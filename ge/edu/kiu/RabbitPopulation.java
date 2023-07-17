package ge.edu.kiu;

public class RabbitPopulation extends MiniJava {
    public static void main(String[] args) {
        int timelength = readInt();
        if (timelength >= 1){
        int[] months = new int[timelength];
        for (int i = 0; i < timelength; i++) {

            months[1] = 1;
            months[i] = months[i - 1] + months[i - 2] + months[i - 3];

        }
        int answ = months[timelength] + months[timelength - 1] + months[timelength + 2];
        System.out.println(answ);
    }else  timelength = readInt();
    }

}