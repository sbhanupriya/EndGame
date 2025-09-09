package sep8_2025;

public class CompareVersionNumbers {
    private int compareVersions(String s, String t){

        int index1 = 0;
        int index2 = 0;
        while(index1<s.length() || index2<t.length()){
            int v1 = 0;
            int v2 = 0;
            //extract v1
            while(index1<s.length() && s.charAt(index1)!='.'){
                v1 = v1*10 + (s.charAt(index1)-'0');
                index1++;
            }
            index1++;
            while(index2<t.length() && t.charAt(index2)!='.'){
                v2 = v2*10 + (t.charAt(index2)-'0');
                index2++;
            }
            index2++;

            if(v1<v2)
                return -1;
            else if(v1>v2)
                return 1;
        }
        return 0;
    }

    public static void main(String[] args){
        CompareVersionNumbers compareVersionNumbers = new CompareVersionNumbers();
        System.out.println(compareVersionNumbers.compareVersions("1.2","1.10"));
    }
}
