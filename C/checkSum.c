/*
Name: Richard Haynes III
Assignment: Security in Computing Checksum
Date: 4/4/2020
 */

/*
  1. First parameter is the name of the file. Second is the
  size in bits of the checksum.
  2. If 8, 16, 32 are not entered for the checksum the user
  should see this: "Valid checksum sizes are 8, 16, or 32". The
  message should be sent to STDERR.
  3. Output the input file (80 characters per row). Print the checksum.
  4. The checksum line: X bit checksum is Y for all ZZZ characters
    - %2d bit checksum is %8lx for all %4 chars\n,
      checkSumSize, checksum, characterCount
  5. X: checksum size, 8, 16, 32
    - Y: calculated checkSumSize
    - ZZZ: character count of input file.
  6. Pad the input file with X if short on characters.
*/


#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <math.h>
#define SIZE 80

int power(int x, int y)
{
    if(y == 0)
        return 1;
    return x * power(x, --y);
}

int main(int argc, char **argv)
{
  int i = 0, j = 0;
  int count = 0;
  int checkSumSize = atoi(argv[2]), checksum = 0, characterCount = 0;
  char text[500];

  char *fname = argv[1];
  FILE *file = fopen(fname, "r");
  if(file == NULL)
  {
    printf("File doesn't exist.\n");
    exit(1);
  }
  else
  {
    printf("File opened successfully.\n");
  }

  do{
      char characters = fgetc(file);
      if(feof(file)){
          break;
      }
      text[i] = characters;
      printf("%c", characters);
      i++;
      if(i % SIZE == 0)
      {
        printf("\n");
      }
  }while(1);
  characterCount = i-1;

  //used to check character count.
  //printf("%d character count\n\n", characterCount);

  if(checkSumSize != 8 && checkSumSize != 16 && checkSumSize != 32)
  {
    fprintf(stderr, "Valid checksum sizes are 8, 16, or 32\n");
    exit(1);
  }

  if(checkSumSize == 8)
  {
    int value[8] = {0,0,0,0,0,0,0,0};
    while(j < characterCount){
        int temp[8] = {0,0,0,0,0,0,0,0};
        int val = text[j];
        //printf("%d\n", val);
        int count = 7, carry = 0;
        while(val > 0){
            temp[count] = val % 2;
            val /= 2;
            count--;
        }
        //printf("Temp: %d, %d, %d, %d, %d, %d, %d, %d\n", temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6], temp[7]);
        for(i = 7; i >= 0; i--){
            if(value[i] == 0 && temp[i] == 0 && carry == 0){
                value[i] = 0;
            }
            else if(((value[i] == 0 && temp[i] == 1) || (value[i] == 1 && temp[i] == 0)) && carry == 0){
                value[i] = 1;
            }
            else if(value[i] == 1 && temp[i] == 1 && carry == 0){
                value[i] = 0;
                carry = 1;
            }
            else if(value[i] == 0 && temp[i] == 0 && carry == 1){
                value[i] = 1;
                carry = 0;
            }
            else if(((value[i] == 0 && temp[i] == 1) || (value[i] == 1 && temp[i] == 0)) && carry == 1){
                value[i] = 0;
            }
            else if(value[i] == 1 && temp[i] == 1 && carry == 1){
                value[i] = 1;
            }
        }
        //printf("Value: %d, %d, %d, %d, %d, %d, %d, %d\n", value[0], value[1], value[2], value[3], value[4], value[5], value[6], value[7]);
        j++;
    }

    j = 0;
    int newline[8] = {0,0,0,0,1,0,1,0};
    for(i = 7; i >= 0; i--){
        if(value[i] == 0 && newline[i] == 0 && j == 0){
            value[i] = 0;
        }
        else if(((value[i] == 0 && newline[i] == 1) || (value[i] == 1 && newline[i] == 0)) && j == 0){
            value[i] = 1;
        }
        else if(value[i] == 1 && newline[i] == 1 && j == 0){
            value[i] = 0;
            j = 1;
        }
        else if(value[i] == 0 && newline[i] == 0 && j == 1){
            value[i] = 1;
            j = 0;
        }
        else if(((value[i] == 0 && newline[i] == 1) || (value[i] == 1 && newline[i] == 0)) && j == 1){
            value[i] = 0;
            j = 1;
        }
        else if(value[i] == 1 && newline[i] == 1 && j == 1){
            value[i] = 1;
            j = 1;
        }
    }
    
    //printf("Value AFTER newline: %d, %d, %d, %d, %d, %d, %d, %d\n", value[0], value[1], value[2], value[3], value[4], value[5], value[6], value[7]);
    j = 0;
    for(i = 7; i >= 0; i--){
        checksum += value[i] * pow(2, j);
        j++;
    }
    printf("%2d bit checksum is %8lx for all %4d chars\n", checkSumSize, checksum, characterCount+1);
  }
  else if(checkSumSize == 16)
  {
    /*
        mod characterCount by 2. if == 0 then no padding
        else add remainder X's.

        TEXT: AB

        A = 01000001
        B = 01000010
        01000001 01000010

        TEXT: A
        A = 01000001
        X = 01011110
        01000001 01011110

    */
   int value[8] = {0,0,0,0,0,0,0,0};
   int flag = 0;
   if((characterCount + 1) % 2 == 1){
       flag = 1;
   }
    while(j < characterCount){
        int temp[8] = {0,0,0,0,0,0,0,0};
        int val = text[j];
        //printf("%d\n", val);
        int count = 15, carry = 0;
        while(val > 0){
            temp[count] = val % 2;
            val /= 2;
            count--;
        }
        j++;
        val = text[j];
        while(val > 0){
            temp[count] = val % 2;
            val /= 2;
            count--;
        }
        //printf("Temp: %d, %d, %d, %d, %d, %d, %d, %d\n", temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6], temp[7]);
        for(i = 15; i >= 0; i--){
            if(i == 7){
                carry = 0;
            }

            if(value[i] == 0 && temp[i] == 0 && carry == 0){
                value[i] = 0;
            }
            else if(((value[i] == 0 && temp[i] == 1) || (value[i] == 1 && temp[i] == 0)) && carry == 0){
                value[i] = 1;
            }
            else if(value[i] == 1 && temp[i] == 1 && carry == 0){
                value[i] = 0;
                carry = 1;
            }
            else if(value[i] == 0 && temp[i] == 0 && carry == 1){
                value[i] = 1;
                carry = 0;
            }
            else if(((value[i] == 0 && temp[i] == 1) || (value[i] == 1 && temp[i] == 0)) && carry == 1){
                value[i] = 0;
            }
            else if(value[i] == 1 && temp[i] == 1 && carry == 1){
                value[i] = 1;
            }
        }
        //printf("Value: %d, %d, %d, %d, %d, %d, %d, %d\n", value[0], value[1], value[2], value[3], value[4], value[5], value[6], value[7]);
        j++;
    }

    j = 0;
    int newline[8] = {0,0,0,0,1,0,1,0};
    for(i = 7; i >= 0; i--){
        if(value[i] == 0 && newline[i] == 0 && j == 0){
            value[i] = 0;
        }
        else if(((value[i] == 0 && newline[i] == 1) || (value[i] == 1 && newline[i] == 0)) && j == 0){
            value[i] = 1;
        }
        else if(value[i] == 1 && newline[i] == 1 && j == 0){
            value[i] = 0;
            j = 1;
        }
        else if(value[i] == 0 && newline[i] == 0 && j == 1){
            value[i] = 1;
            j = 0;
        }
        else if(((value[i] == 0 && newline[i] == 1) || (value[i] == 1 && newline[i] == 0)) && j == 1){
            value[i] = 0;
            j = 1;
        }
        else if(value[i] == 1 && newline[i] == 1 && j == 1){
            value[i] = 1;
            j = 1;
        }
    }
    
    if(flag == 1){
        /*Do X summation here*/
    }
    
    //printf("Value AFTER newline: %d, %d, %d, %d, %d, %d, %d, %d\n", value[0], value[1], value[2], value[3], value[4], value[5], value[6], value[7]);
    j = 0;
    for(i = 15; i >= 0; i--){
        if(i == 7){
            j = 0;
        }
        checksum += value[i] * pow(2, j);
        j++;
    }
    printf("%2d bit checksum is %8lx for all %4d chars\n", checkSumSize, checksum, characterCount+1);

  }
  else if(checkSumSize == 32)
  {
    /*
        mod characterCount by 4. if == 0 then no padding
        else add remainder X's
    */
  }
}//end of main
