package cn.marding.utils;

import java.lang.reflect.Field;
import java.util.Iterator;

/**
 * 码丁集合1.1 更加完善的有序数据存储集合
<<<<<<< HEAD 2·12112·
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
	// 用来存放数据
	public Object[] elementData;

	// 集合元素个数/数组长度
	private int size;

	/**
	 * 返回 此集合中元素的个数
	 * 
	 * @return 集合个数
	 */
	public int size() {
		return this.size;
	}

	/**
	 * 添加一个元素到集合中
	 * 
	 * @param o
	 *            - 要添加到集合中的元素
	 * @return 添加成功或失败
	 */
	public boolean add(W o) {
		// 针对 新创建的对象(空对象) 或者 移除所有元素的老对象
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
	 * 从集合中获取一个元素
	 * 
	 * @param index
	 *            - 想要获取元素的索引位置
	 * @return 指定索引的元素,获取失败 则为 null
	 */
	public W get(int index) {
		if (index >= size)
			throw new IndexOutOfBoundsException("数组越界异常");
		return (W) elementData[index];
	}

	/**
	 * 移除指定索引的元素
	 * 
	 * @param index
	 *            - 想要移除元素的索引位置
	 * @return 移除成功或失败
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
	 * 移除集合中的指定元素 如果集合中存在同样的多个元素,又不想全部移除,请参考 - <B>index<B>
	 * 
	 * @param o
	 *            - 想要移除的指定元素
	 * @param index
	 *            - 不定参数,如不填写则移除指定元素,如填写则移除相同多个元素中指定索引的元素
	 * @return 移除成功或失败
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
	 * 对集合指定索引位置的元素进行赋值
	 * 
	 * @param index
	 *            - 指定的索引位置
	 * @param o
	 *            - 想要赋值的元素值
	 * @return 赋值状态成功或者失败
	 */
	public boolean set(int index, Object o) {
		elementData[index] = o;
		return true;
	}

	/**
	 * 判断此集合当中是否包含指定元素
	 * 
	 * @param o
	 *            - 被判断包含的元素
	 * @return 是否包含
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
