import java.io.*;
import java.util.*;
public class TopTenList{
public final static int MAX=10;
private
LinkNode front=null;
LinkNode current=null;
int count=0;
  public TopTenList(){
   front=null;
  }
  public TopTenList(String fileName)throws FileNotFoundException{
    Scanner file = new Scanner (new File(fileName));
    while (file.hasNextLine()){
      count++;
      String name=file.nextLine().trim();
      LinkNode element = new LinkNode(count,name);
      //System.out.println(name);
      if (front ==null )
         {
           front= element;
           current=element;
         }
      else current.setNext(element);
      current=element;
      //System.out.println(current.getSong());
    }
  }
  void printChart(){
    System.out.println("\n***** TOP TEN CHART **********\n");
    current=front;
    for (int i=1;i<=count;i++){    
      if(this.current != null){
        System.out.printf("%4d%26s\n",current.getPosition(),current.getSong());
        current = current.getNext();
        
      }
    }
  }
 String getSong(int position){
  String name = "ERROR";
  LinkNode t = front;
  if (position <1 || position > count)
     return name;
  else {
    for (int i=1;i<position;i++)
      t=t.getNext();
      name = t.getSong();
      return name;
    }
 }
 int getPosition(String song){
   current = front;
   int pos = 0;
   
    if (current.getSong().equalsIgnoreCase(song))
      pos = current.getPosition();
      current=current.getNext();
      
   return pos;
 }
  void adjustPosition(LinkNode e,int position){
   if (e!=null)
    while (e!=null){
      e.setPosition(e.getPosition()+position);
      e=e.getNext();
    }
 }

  LinkNode removeLastNode(){
    LinkNode second_last = front;
    while (second_last.next.next != null)
      second_last = second_last.next;
      second_last.next = null;
      return front;
  }

// COMPLETE THE METHODS BELOW
 boolean deleteSong(int position){
   if(position > 0 && position < 11){
     if(position == 1){
       front = front.next;
       count--;
       adjustPosition(front, -1);
       return true;
      }
  
     int index = 1;
     
     current = front;
     while(current != null && current.next != null && index < position){
       if(current.next.getPosition() == position){
         current.next = current.next.next;
       }
       current = current.next;
       index++;
     }
     count--;
     adjustPosition(current, -1);
     return true; 
   } else {
     return false;
   }
 }

 boolean insertSong(int position,String song){
   //insert your code here
   if(position > 0 && position < 11){
     LinkNode newNode = new LinkNode(position, song);
     LinkNode prev = front;
     current = front;
     int index = 1;
     if(position > count || position < 0){
       return false;
     } else {
       while(index < position){
         prev = current;
         current = current.next;
         index++;
       }
       newNode.next = current;
       prev.next = newNode;
       count++;
       adjustPosition(current, +1);
       removeLastNode();
       return true;
     }
   } else {
     return false;
   }
 }

 boolean moveSong(int oldPosition, int newPosition){
   //insert your code here 
   String basket = getSong(oldPosition);
   if(deleteSong(oldPosition)){
     return insertSong(newPosition, basket);
   }
   return true; //change this as needed
 }
}