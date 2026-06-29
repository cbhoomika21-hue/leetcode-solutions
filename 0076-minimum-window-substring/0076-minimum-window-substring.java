import java.util.HashMap;
import java.util.Map;
class Solution{
public String minWindow(String s,String t){
if(s==null||t==null||s.length()<t.length()){
return"";
}
Map<Character,Integer>tFreq=new HashMap<>();
for(char c:t.toCharArray()){
tFreq.put(c,tFreq.getOrDefault(c,0)+1);
}
int required=tFreq.size();
int left=0,right=0;
int formed=0;
Map<Character,Integer>windowFreq=new HashMap<>();
int[]ans={-1,0,0};
while(right<s.length()){
char c=s.charAt(right);
windowFreq.put(c,windowFreq.getOrDefault(c,0)+1);
if(tFreq.containsKey(c)&&windowFreq.get(c).intValue()==tFreq.get(c).intValue()){
formed++;
}
while(left<=right&&formed==required){
c=s.charAt(left);
if(ans[0]==-1||(right-left+1)<ans[0]){
ans[0]=right-left+1;
ans[1]=left;
ans[2]=right;
}
windowFreq.put(c,windowFreq.get(c)-1);
if(tFreq.containsKey(c)&&windowFreq.get(c).intValue()<tFreq.get(c).intValue()){
formed--;
}
left++;
}
right++;
}
return ans[0]==-1?"":s.substring(ans[1],ans[2]+1);
}
}