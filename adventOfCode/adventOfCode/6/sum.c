#include <stdio.h>
int getVal(long int totalTime, long int hold, long int dist){
  int tempTime=totalTime-hold;
  if (tempTime<=0){
    return -1;
  }
  if ((tempTime)*hold<=dist){
    return -1;
  }
  return 1;
}
int main(){
  long int time1=54708275;
  long int dist1=239114212951253;
  int time2=1;
  int time3=82;
  int time4=75;
  int dist2=1142;
  int dist3=1295;
  int dist4=1253;
  
  int times1=0;
  {
  for (long hold=0; hold<=time1; hold++){
    int tempg=getVal(time1, hold, dist1);
    if (tempg==1){
      times1++;
    }
  }
  printf("%d\n",times1);
  }
  /**
  int times2=0;
  {
  for (int hold=0; hold<=time2; hold++){
    int tempg=getVal(time2, hold, dist2);
    if (tempg==1){
      times2++;
    }
  }
  printf("%d\n",times2);
  }
  int times3=0;
  {
  for (int hold=0; hold<=time3; hold++){
    int tempg=getVal(time3, hold, dist3);
    if (tempg==1){
      times3++;
    }
  }
  printf("%d\n",times3);
  }
  int times4=0;
  {
  for (int hold=0; hold<=time4; hold++){
    int tempg=getVal(time4, hold, dist4);
    if (tempg==1){
    times4++;
    }
  }
  printf("%d\n",times4);
  }
  int out=times1*times2*times3*times4;
  printf("%d\n",out);
  */
  return 0;
}
