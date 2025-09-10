package sep9_2025;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MaximumNumberOfVisiblePoints {
    //LC 1610
    //1) Modify Data to have angle between source & currPoint
    //2) Sort the points in ascending order
    //3) Sliding over angles such that window angle is less than the turn
    //4) Time complexity O(NLOGN) Space O(N)
    //5) It should be circular sliding window
    //6) Edge Case - Point Same as Location - Just increment the final ans
    //7) Edge Case - When all points represent same as location
    //8) Syntax issue in comparator

    public int maxVisible(List<List<Integer>> points,int angle, List<Integer> location){
        List<Double> angles = new ArrayList<>();
        int sameLocation = 0;
        for(int i=0;i<points.size();i++){
            Double diffY = (double) (points.get(i).get(1) - location.get(1));
            Double diffX = (double)points.get(i).get(0) - location.get(0);
            Double theta = (Math.toDegrees(Math.atan2(diffY,diffX))+360)%360;
            if(diffY==0.0 && diffX==0.0){
                sameLocation++;
            } else
                angles.add(theta);
        }
        int start = 0;
        int end = 1;
        int maxCount = angles.size()>0 ? 1: 0;
        int curr = angles.size()>0? 1:0;

        Collections.sort(angles,(a,b) -> {
            if(a<b){
                return -1;
            } else {
                return 1;
            }
        });

        int size = angles.size();
        for(int i=0;i<size;i++){
            angles.add(angles.get(i));
        }

        while(end<angles.size()){
            double diff = (360 + angles.get(end)-angles.get(start)+360)%360;
            while(diff>angle || ((end-start+1)>size && start+1<angles.size())) {
                curr--;
                start++;
                diff = (360 + angles.get(end) - angles.get(start)+360) % 360;
            }
            if(diff<=angle){
                curr++;
                maxCount = Math.max(maxCount, curr);
            }
            end = end+1;
        }

        return maxCount + sameLocation;
    }

    public static void main(String[] args){
        MaximumNumberOfVisiblePoints maximumNumberOfVisiblePoints = new MaximumNumberOfVisiblePoints();
        List<Integer> location = Arrays.asList(1,1);
        int angle = 0;
        List<List<Integer>> points = new ArrayList<>();
        points.add(Arrays.asList(1,1));
        points.add(Arrays.asList(2,2));
        points.add(Arrays.asList(3,3));
        points.add(Arrays.asList(4,4));
        points.add(Arrays.asList(1,2));
        points.add(Arrays.asList(2,1));
        System.out.println(maximumNumberOfVisiblePoints.maxVisible(points,angle,location));
    }

}
