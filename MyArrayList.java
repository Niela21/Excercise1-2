public class MyArrayList<E> 
{
  private int size; // Number of elements in the list
  private E[] data;
  private int MAXELEMENTS = 100;
  /** Create an empty list */
  public MyArrayList() {
	   data = (E[])new Object[MAXELEMENTS];// cannot create array of generics
       size = 0; // Number of elements in the list
  }
  
  public int getMAXELEMENTS(){
	  return MAXELEMENTS;
  }
	  
   
  
  public boolean checkSpace()
  {
	  if (size+1<MAXELEMENTS)
		  return true;
	  else
		  return false;
  }
  
  public void add(int index, E e) {   
    // Ensure the index is in the right range
    if (index < 0 || index > size)
      throw new IndexOutOfBoundsException
        ("Index: " + index + ", Size: " + size); 
    // Move the elements to the right after the specified index
    for (int i = size - 1; i >= index; i--)
      data[i + 1] = data[i];
    // Insert new element to data[index]
    data[index] = e;
    // Increase size by 1
    size++;
  }

  public boolean contains(Object e) {
    for (int i = 0; i < size; i++)
      if (e.equals(data[i])) return true;
    return false;
  }

  public E get(int index) {
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException
        ("Index: " + index + ", Size: " + size);
    return data[index];
  }
  
  public E remove(int index) {
	if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException
        ("Index: " + index + ", Size: " + size);
    E e = data[index];
    // Shift data to the left
    for (int j = index; j < size - 1; j++)
      data[j] = data[j + 1];
    data[size - 1] = null; // This element is now null
    // Decrement size
    size--;
    return e;
  }
  
  public void clear()
  {
     size = 0;
  }
 
  public MyArrayList<E> merge(MyArrayList<E> param)
  {
	  int i=0; //counter in calling array
	  int j=0; // counter in param array
	  int k=0; // counter in return array
	  MyArrayList<E> returnArray = new MyArrayList();
	  
	  if (this.getSize() ==0) // same as if (size==0)
		  return param;
	  if (param.getSize()==0)
		  return this;
	  if ((this.getSize()+ param.getSize()) > MAXELEMENTS)
		   throw new IndexOutOfBoundsException
        ("Combined list out of bounds");
		
	  // traverse both list until one list is completely done
	  while (i<this.getSize() && j<param.getSize())
	  {
		  // Compare single value from each list and copy smallest into result
		  if (((Comparable)data[i]).compareTo(param.data[j]) <0)
		  {
			returnArray.data[k]= this.data[i];
			k++;
			i++;	
		  }
		  else
		  {
			returnArray.data[k]=param.data[j];
			k++;
			j++;
		  }
	  }
	  
	  // copy remainder of the array
	  if (i < this.getSize())
	  {
		  for (i=i;i<getSize();i++) //for starts at current position
		  {
			returnArray.data[k]= this.data[i];
			k++;
		  }
	  }
	  if (j < param.getSize())
	  {
		  for (j=j;j<param.getSize();j++)
		  {
			returnArray.data[k]=param.data[j];
			k++;
		  }
	  }
	  returnArray.size = k; // set size of return array
	  return returnArray;
  }		  
		  
	  
  public String toString() {
    String result="[";
    for (int i = 0; i < size; i++) {
      result+= data[i];
      if (i < size - 1) result+=", ";
    }
    return result.toString() + "]";
  }

  
  public int getSize() {
    return size;
  }
  
 public boolean sortList() {
    E hold;
	for (int i = 0; i < size-1; i++)
	 {
	   for (int j = 0; j<size-1; j++)
	    {  	 
	     if(((Comparable)data[j]).compareTo(data[j+1])>0)
	      {
	       hold= data[j+1];
	       data[j+1]=data[j];
	       data[j]=hold;
	      }       
	   }
     } 
	 return true;	  	
  }
  
  public boolean checkUniform()
  {
	  boolean flag = false;
	  if(size < 4)//Special case if there is less then 4 items in the bottle
		  return flag;
		  
		  
	  
	  for (int i = 0; i < size-1; i++)
      {
		  if(((Comparable)data[i]).compareTo(data[i+1])!=0)
		  {
			  return flag;//if they array is not uniform return false
		  }
	  }
	  
	  return true;//if the array passed the check return true
  }


 
}