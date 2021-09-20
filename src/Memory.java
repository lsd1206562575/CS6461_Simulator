//Simple memory class
public class Memory {
    //setting memory to 2048 and exp to 4096
    public Object[][] memoryArray = new Object[2048][2];

    public Memory(){
        for (int i=0;i<2048;i++){
            memoryArray[i][0]  =i;
            memoryArray[i][1] = "0000,0000,0000,0000";

        }
    }

    @Override
    public String toString(){
        StringBuilder output = new StringBuilder();
        for (Object[] objects : this.memoryArray) {
            output.append(objects[0]).append(":").append(objects[1]).append("\n");
        }
        return output.toString();

    }
    //show used memory as a string output,
    /*
    public StringArray showUsedMemory(){
        StringArray output = new StringArray();
        for (int i = 0 , j = 0; i < this.memoryArray.length; i++) {
            if (this.memoryArray[i][1] != "0000,0000,0000,0000") {
                output.add(this.memoryArray[i][1]);
                j++;
            }
            if(output.equals(" ")) {
                output = "Nothing in memory";
            }

        }
        return output;

    }
    */

    //for testing
    public static void main(String[] args){

        System.out.println(new Memory());
        //System.out.println(new Memory().showUsedMemory());
    }
    public void putMem(int i ,String value){
        memoryArray[i-1][0]=Integer.parseInt(String.valueOf(i),10);
        memoryArray[i-1][1]=value;
    }
}
