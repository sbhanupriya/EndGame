package sep8_2025;

public class OneEditDistance {

    // length of S<L (Insert 1) if mismatch (t pointer ++)
    // length of S>L (Delete 1) if mismatch (s pointer ++)
    // length of S==L (ignore 1 if mismatch (t,s pointer ++)
    // time complexity O(N+M) Space O(1)

    public boolean editDistance(String s, String t){
        if(Math.abs(s.length()-t.length())>=2)
            return false;
        int index1 = 0;
        int index2 = 0;
        int mismatch = 0;
        while(index1<s.length() && index2<t.length()){
            if(s.charAt(index1)==t.charAt(index2))
            {
                index1++;
                index2++;
                continue;
            }
            mismatch++;
            index2 += s.length()>t.length()?0:1;
            index1 += s.length()<t.length()?0:1;
        }
        mismatch += ((s.length()-index1)+(t.length()-index2));
        return mismatch==1;
    }

    public static void main(String[] args){
        OneEditDistance editDistance = new OneEditDistance();
        System.out.println(editDistance.editDistance("BHANU", "HANU"));
    }
}
