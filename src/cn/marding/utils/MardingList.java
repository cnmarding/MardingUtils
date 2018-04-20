package cn.marding.utils;

import java.lang.reflect.Field;
import java.util.Iterator;

/**
 * �붡����1.1 �������Ƶ��������ݴ洢����
<<<<<<< HEAD 2��12112��
<<<<<<< HEAD
 * @version 1.0123456
 * @author Antony
=======
 * @version 1.0
 * @author Antonylllll
>>>>>>> refs/remotes/origin/master
 * @since 2018-4-18
 * ssaddas 21213322332 ====12312321
=======
>>>>>>> branch 'master' of https://github.com/cnmarding/MardingUtils.git
 */
public class MardingList<W> implements Iterable {
	// �����������
	public Object[] elementData;

	// ����Ԫ�ظ���/���鳤��
	private int size;

	/**
	 * ���� �˼�����Ԫ�صĸ���
	 * 
	 * @return ���ϸ���
	 */
	public int size() {
		return this.size;
	}

	/**
	 * ���һ��Ԫ�ص�������
	 * 
	 * @param o
	 *            - Ҫ��ӵ������е�Ԫ��
	 * @return ��ӳɹ���ʧ��
	 */
	public boolean add(W o) {
		// ��� �´����Ķ���(�ն���) ���� �Ƴ�����Ԫ�ص��϶���
		if (elementData == null || elementData.length == 0) {
			elementData = new Object[1];
			size++;
			elementData[this.size - 1] = o;
		} else {
			size++;
			ensureCapacityInternal(o);
			elementData[this.size - 1] = o;
		}

		return true;
	}

	public int count = 0;

	private void ensureCapacityInternal(W o) {
		Object[] newElementData = null;
		if (elementData.length > this.size) {
			elementData[size - 1] = o;
		} else {
			count++;
			newElementData = new Object[elementData.length * 2];
			for (int i = 0; i < elementData.length; i++) {
				newElementData[i] = elementData[i];
			}
			elementData = newElementData;
		}

	}

	/**
	 * �Ӽ����л�ȡһ��Ԫ��
	 * 
	 * @param index
	 *            - ��Ҫ��ȡԪ�ص�����λ��
	 * @return ָ��������Ԫ��,��ȡʧ�� ��Ϊ null
	 */
	public W get(int index) {
		if (index >= size)
			throw new IndexOutOfBoundsException("����Խ���쳣");
		return (W) elementData[index];
	}

	/**
	 * �Ƴ�ָ��������Ԫ��
	 * 
	 * @param index
	 *            - ��Ҫ�Ƴ�Ԫ�ص�����λ��
	 * @return �Ƴ��ɹ���ʧ��
	 * 
	 */
	public boolean remove(int index) {
		elementData[index] = null;
		Object[] newElementData = new Object[--size];
		int split = -1;
		for (int i = 0; i < elementData.length; i++) {
			if (elementData[i] != null) {
				newElementData[i] = elementData[i];
			} else {
				split = i;
				break;
			}
		}
		for (; split < newElementData.length; split++) {
			newElementData[split] = elementData[split + 1];
		}

		elementData = newElementData;

		return true;
	}

	/**
	 * �Ƴ������е�ָ��Ԫ�� ��������д���ͬ���Ķ��Ԫ��,�ֲ���ȫ���Ƴ�,��ο� - <B>index<B>
	 * 
	 * @param o
	 *            - ��Ҫ�Ƴ���ָ��Ԫ��
	 * @param index
	 *            - ��������,�粻��д���Ƴ�ָ��Ԫ��,����д���Ƴ���ͬ���Ԫ����ָ��������Ԫ��
	 * @return �Ƴ��ɹ���ʧ��
	 * 
	 */
	public boolean remove(Object o, Integer... index) {
		for (int i = 0; i < elementData.length; i++) {
			if (elementData[i].equals(o)) {
				elementData[i] = null;
				break;
			}
		}
		Object[] newElementData = new Object[--size];
		int split = -1;
		for (int i = 0; i < elementData.length; i++) {
			if (elementData[i] != null) {
				newElementData[i] = elementData[i];
			} else {
				split = i;
				break;
			}
		}
		for (; split < newElementData.length; split++) {
			newElementData[split] = elementData[split + 1];
		}
		elementData = newElementData;
		return true;
	}

	/**
	 * �Լ���ָ������λ�õ�Ԫ�ؽ��и�ֵ
	 * 
	 * @param index
	 *            - ָ��������λ��
	 * @param o
	 *            - ��Ҫ��ֵ��Ԫ��ֵ
	 * @return ��ֵ״̬�ɹ�����ʧ��
	 */
	public boolean set(int index, Object o) {
		elementData[index] = o;
		return true;
	}

	/**
	 * �жϴ˼��ϵ����Ƿ����ָ��Ԫ��
	 * 
	 * @param o
	 *            - ���жϰ�����Ԫ��
	 * @return �Ƿ����
	 * 
	 */
	public boolean contains(Object o) {
		for (int i = 0; i < elementData.length; i++) {
			if (elementData[i].equals(o))
				return true;
		}
		return false;
	}

	@Override
	public String toString() {
		String value = "{\n";
		value+="\tsize:"+this.size+"\n";
		if (size != 0) {
			for (int i = 0; i < size; i++) {

				Class clx = elementData[i].getClass();
				value += "\t[\n\t\t" + clx.getName();
				value += "\n\t\tField:Value:\n";
				Field [] fields  = clx.getDeclaredFields();
				for (int j = 0; j < fields.length; j++) {
					fields[j].setAccessible(true);
					value += "\t\t"+fields[j].getName() + ":";
					try {
						value +=fields[j].get(elementData[i]) + "\n";
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(i==size-1)
					value += "\t]\n}\n";
				else
					value+="\t]\n";
			}
			return value;
		} else {
			return "[ ]";
		}

	}

	@Override
	public Iterator<W> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
