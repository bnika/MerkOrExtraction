package is.merkor.util;

import java.util.ArrayList;

public class NamedArrayList<E> extends ArrayList<E> {

		private String name;
		
		public NamedArrayList() {
			this.name = "";
		}
		public NamedArrayList(String name) {
			this.name = name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getName() {
			return name;
		}
		public boolean hasName(String name) {
			return this.name.equals(name);
		}
		
		public String toString() {
			return this.name + ": " + super.toString();
		}
		public boolean equals(NamedArrayList<E> anotherList) {
			System.out.println("comparing " + this.name + " ... ");
//			if(this.size() != anotherList.size()) 
//				return false;
			//System.out.println(this.toString());
			boolean equal = false;
			boolean allEqual = true;
//			int size = 0;
//			int thisSize = this.size();
//			int otherSize = anotherList.size();
//			
//			if (thisSize <= otherSize) {
//				size = thisSize;
//				System.out.println("Size = thisSize: " + size);
//			}
//			else {
//				size = otherSize;
//				System.out.println("Size = otherSize: " + size);
//			}
			
			int j = 0;
			for(int i = 0; i < this.size(); i++) {
				//System.out.println(i + ": " + this.get(i) + "another at " + j + ": " + anotherList.get(j));
				if(anotherList.size() <= j)
					break;
				if(this.get(i).equals(anotherList.get(j))) {
					equal = true;
					//System.out.println("EQUAL: " + this.get(i) + " -- " + anotherList.get(j));
				}
				else {
					equal = false;
					if (i > 10)
						j = i - 10;
					else
						j = 0;
					while((j < (i+10) && j < anotherList.size()) && !equal) {
						//System.out.println(anotherList.get(j) + " is not in the result table!");
						if(this.get(i).equals(anotherList.get(j))) {
							equal = true;
							//System.out.println("EQUAL: " + this.get(i) + " -- " + anotherList.get(j));
						}
						j++;
					}
					if(!equal) {
						j = i+1;
						System.out.println(this.get(i) + " was not found in the result file!");
						allEqual = false;
					}
				}
			}
			return allEqual;
		}
}

