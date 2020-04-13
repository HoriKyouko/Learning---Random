#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <limits.h>
#include <math.h>
#define SIZE 80

unsigned int power(int x, int y)
{
    if(y == 0)
        return 1;
    return x * power(x, --y);
}

int main(int argc, char **argv)
{
    int i = 0, j = 0;
    int count = 0;
    int checkSumSize = atoi(argv[2]), characterCount = 0;
    unsigned int checksum = 0;
    char text[500];

    char *fname = argv[1];
    FILE *file = fopen(fname, "r");
    if(file == NULL)
    {
        printf("File doesn't exist.\n");
        exit(1);
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
            int count = 7, carry = 0;

            while(val > 0){
                temp[count] = val % 2;
                val /= 2;
                count--;
            }
        
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
            }
            else if(value[i] == 1 && newline[i] == 1 && j == 1){
                value[i] = 1;
            }
        }
    
        j = 0;
        for(i = 7; i >= 0; i--){
            checksum += value[i] * pow(2, j);
            j++;
        }
        printf("%2d bit checksum is %8lx for all %4d chars\n", checkSumSize, checksum, characterCount+1);
    }
    else if(checkSumSize == 16)
    {
        int value[16] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        int flag = 0;
        if((characterCount + 1) % 2 == 1){
            flag = 1;
        }

        if(flag == 0){
            while(j < characterCount - 1){
                int temp[16] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
                int count = 7, carry = 0, val = text[j];
                while(val > 0){
                    temp[count] = val % 2;
                    val /= 2;
                    count--;
                }
                j++;
                val = text[j];
                count = 15;
                while(val > 0){
                    temp[count] = val % 2;
                    val /= 2;
                    count--;
                }
                for(i = 15; i >= 0; i--){
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
                j++;
            }
            /*Get second to last character and then also newline.*/
            int temp[16] = {0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0};
            int val = text[j], count = 7;
            while(val > 0){
                temp[count] = val % 2;
                val /= 2;
                count--;
            }
            j = 0;
            for(i = 15; i >= 0; i--){
                if(value[i] == 0 && temp[i] == 0 && j == 0){
                    value[i] = 0;
                }
                else if(((value[i] == 0 && temp[i] == 1) || (value[i] == 1 && temp[i] == 0)) && j == 0){
                    value[i] = 1;
                }
                else if(value[i] == 1 && temp[i] == 1 && j == 0){
                    value[i] = 0;
                    j = 1;
                }
                else if(value[i] == 0 && temp[i] == 0 && j == 1){
                    value[i] = 1;
                    j = 0;
                }
                else if(((value[i] == 0 && temp[i] == 1) || (value[i] == 1 && temp[i] == 0)) && j == 1){
                    value[i] = 0;
                }
                else if(value[i] == 1 && temp[i] == 1 && j == 1){
                    value[i] = 1;
                }
            }
        }
        else{
            while(j < characterCount){
                int temp[16] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
                int count = 7, carry = 0, val = text[j];

                while(val > 0){
                    temp[count] = val % 2;
                    val/= 2;
                    count--;
                }
                j++;
                val = text[j];
                count = 15;
                while(val > 0){
                    temp[count] = val % 2;
                    val /= 2;
                    count--;
                }
                for(i = 15; i >= 0; i--){
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
                j++;
            }

            j = 0;
            int temp[16] = {0,0,0,0,1,0,1,0,0,1,0,1,1,0,0,0};
            for(i = 15; i >= 0; i--){
                if(value[i] == 0 && temp[i] == 0 && j == 0){
                    value[i] = 0;
                }
                else if(((value[i] == 0 && temp[i] == 1) || (value[i] == 1 && temp[i] == 0)) && j == 0){
                    value[i] = 1;
                }
                else if(value[i] == 1 && temp[i] == 1 && j == 0){
                    value[i] = 0;
                    j = 1;
                }
                else if(value[i] == 0 && temp[i] == 0 && j == 1){
                    value[i] = 1;
                    j = 0;
                }
                else if(((value[i] == 0 && temp[i] == 1) || (value[i] == 1 && temp[i] == 0)) && j == 1){
                    value[i] = 0;
                }
                else if(value[i] == 1 && temp[i] == 1 && j == 1){
                    value[i] = 1;
                }
            }
        }

        j = 0;
        for(i = 15; i >= 0; i--){
            checksum += value[i] * pow(2, j);
            j++;
        }

        if(flag == 1){
            printf("X\n");
            printf("%2d bit checksum is %8lx for all %4d chars\n", checkSumSize, checksum, characterCount+2);
        }
        else{
            printf("%2d bit checksum is %8lx for all %4d chars\n", checkSumSize, checksum, characterCount+1);
        }
    }
    else if(checkSumSize == 32)
    {
        int value[32] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        int flag = 4 - ((characterCount + 1) % 4);

        if(flag == 4){ // need 0 X
            while(j < characterCount - 3){
                int temp[32] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
                int count = 7, carry = 0, val = text[j];
                while(val > 0){
                    temp[count] = val % 2;
                    val /= 2;
                    count--;
                }
                j++;
                val = text[j];
                count = 15;
                while(val > 0){
                    temp[count] = val % 2;
                    val /= 2;
                    count--;
                }
                j++;
                val = text[j];
                count = 23;
                while(val > 0){
                    temp[count] = val % 2;
                    val /= 2;
                    count--;
                }
                j++;
                val = text[j];
                count = 31;
                while(val > 0){
                    temp[count] = val % 2;
                    val /= 2;
                    count--;
                }

                for(i = 31; i >= 0; i--){
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
                j++;
            }
            
            int temp[32] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0};
            int val = text[j], count = 7;
            while(val > 0){
                temp[count] = val % 2;
                val /= 2;
                count--;
            }
            j++;
            val = text[j];
            count = 15;
            while(val > 0){
                temp[count] = val % 2;
                val /= 2;
                count--;
            }
            j++;
            val = text[j];
            count = 23;
            while(val > 0){
                temp[count] = val % 2;
                val /= 2;
                count--;
            }
            j = 0;
            for(i = 31; i >= 0; i--){
                if(value[i] == 0 && temp[i] == 0 && j == 0){
                    value[i] = 0;
                }
                else if(((value[i] == 0 && temp[i] == 1) || (value[i] == 1 && temp[i] == 0)) && j == 0){
                    value[i] = 1;
                }
                else if(value[i] == 1 && temp[i] == 1 && j == 0){
                    value[i] = 0;
                    j = 1;
                }
                else if(value[i] == 0 && temp[i] == 0 && j == 1){
                    value[i] = 1;
                    j = 0;
                }
                else if(((value[i] == 0 && temp[i] == 1) || (value[i] == 1 && temp[i] == 0)) && j == 1){
                    value[i] = 0;
                }
                else if(value[i] == 1 && temp[i] == 1 && j == 1){
                    value[i] = 1;
                }
            }
        }
        else if(flag == 1){ // need 1 X
            while(j < characterCount - 2){
                int temp[32] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
                int count = 7, carry = 0, val = text[j];
                while(val > 0){
                    temp[count] = val % 2;
                    val /= 2;
                    count--;
                }
                j++;
                val = text[j];
                count = 15;
                while(val > 0){
                    temp[count] = val % 2;
                    val /= 2;
                    count--;
                }
                j++;
                val = text[j];
                count = 23;
                while(val > 0){
                    temp[count] = val % 2;
                    val /= 2;
                    count--;
                }
                j++;
                val = text[j];
                count = 31;
                while(val > 0){
                    temp[count] = val % 2;
                    val /= 2;
                    count--;
                }

                for(i = 31; i >= 0; i--){
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
                j++;
            }
            
            int temp[32] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,1,0,1,1,0,0,0};
            int val = text[j], count = 7;
            while(val > 0){
                temp[count] = val % 2;
                val /= 2;
                count--;
            }
            j++;
            val = text[j];
            count = 15;
            while(val > 0){
                temp[count] = val % 2;
                val /= 2;
                count--;
            }
            //printf("%d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d\n",temp[0],temp[1],temp[2],temp[3],temp[4],temp[5],temp[6],temp[7],temp[8],temp[9],temp[10],temp[11],temp[12],temp[13],temp[14],temp[15],temp[16],temp[17],temp[18],temp[19],temp[20],temp[21],temp[22],temp[23],temp[24],temp[25],temp[26],temp[27],temp[28],temp[29],temp[30],temp[31]);
            j = 0;
            for(i = 31; i >= 0; i--){
                if(value[i] == 0 && temp[i] == 0 && j == 0){
                    value[i] = 0;
                }
                else if(((value[i] == 0 && temp[i] == 1) || (value[i] == 1 && temp[i] == 0)) && j == 0){
                    value[i] = 1;
                }
                else if(value[i] == 1 && temp[i] == 1 && j == 0){
                    value[i] = 0;
                    j = 1;
                }
                else if(value[i] == 0 && temp[i] == 0 && j == 1){
                    value[i] = 1;
                    j = 0;
                }
                else if(((value[i] == 0 && temp[i] == 1) || (value[i] == 1 && temp[i] == 0)) && j == 1){
                    value[i] = 0;
                }
                else if(value[i] == 1 && temp[i] == 1 && j == 1){
                    value[i] = 1;
                }
            }
            //printf("%d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d\n",value[0],value[1],value[2],value[3],value[4],value[5],value[6],value[7],value[8],value[9],value[10],value[11],value[12],value[13],value[14],value[15],value[16],value[17],value[18],value[19],value[20],value[21],value[22],value[23],value[24],value[25],value[26],value[27],value[28],value[29],value[30],value[31]);
            
        }
        else if(flag == 2){ // need 2 X
            while(j < characterCount - 1){
                int temp[32] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
                int count = 7, carry = 0, val = text[j];
                while(val > 0){
                    temp[count] = val % 2;
                    val /= 2;
                    count--;
                }
                j++;
                val = text[j];
                count = 15;
                while(val > 0){
                    temp[count] = val % 2;
                    val /= 2;
                    count--;
                }
                j++;
                val = text[j];
                count = 23;
                while(val > 0){
                    temp[count] = val % 2;
                    val /= 2;
                    count--;
                }
                j++;
                val = text[j];
                count = 31;
                while(val > 0){
                    temp[count] = val % 2;
                    val /= 2;
                    count--;
                }

                for(i = 31; i >= 0; i--){
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
                j++;
            }

            int temp[32] = {0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,1,0,1,1,0,0,0,0,1,0,1,1,0,0,0};
            int val = text[j], count = 7;
            while(val > 0){
                temp[count] = val % 2;
                val /= 2;
                count--;
            }
            j = 0;
            for(i = 31; i >= 0; i--){
                if(value[i] == 0 && temp[i] == 0 && j == 0){
                    value[i] = 0;
                }
                else if(((value[i] == 0 && temp[i] == 1) || (value[i] == 1 && temp[i] == 0)) && j == 0){
                    value[i] = 1;
                }
                else if(value[i] == 1 && temp[i] == 1 && j == 0){
                    value[i] = 0;
                    j = 1;
                }
                else if(value[i] == 0 && temp[i] == 0 && j == 1){
                    value[i] = 1;
                    j = 0;
                }
                else if(((value[i] == 0 && temp[i] == 1) || (value[i] == 1 && temp[i] == 0)) && j == 1){
                    value[i] = 0;
                }
                else if(value[i] == 1 && temp[i] == 1 && j == 1){
                    value[i] = 1;
                }
            }
        }
        else if(flag == 3){ // need 3 X
            while(j < characterCount){
                int temp[32] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
                int count = 7, carry = 0, val = text[j];
                while(val > 0){
                    temp[count] = val % 2;
                    val /= 2;
                    count--;
                }
                j++;
                val = text[j];
                count = 15;
                while(val > 0){
                    temp[count] = val % 2;
                    val /= 2;
                    count--;
                }
                j++;
                val = text[j];
                count = 23;
                while(val > 0){
                    temp[count] = val % 2;
                    val /= 2;
                    count--;
                }
                j++;
                val = text[j];
                count = 31;
                while(val > 0){
                    temp[count] = val % 2;
                    val /= 2;
                    count--;
                }

                for(i = 31; i >= 0; i--){
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
                j++;
            }
            
            int temp[32] = {0,0,0,0,1,0,1,0,0,1,0,1,1,0,0,0,0,1,0,1,1,0,0,0,0,1,0,1,1,0,0,0};
            j = 0;
            for(i = 31; i >= 0; i--){
                if(value[i] == 0 && temp[i] == 0 && j == 0){
                    value[i] = 0;
                }
                else if(((value[i] == 0 && temp[i] == 1) || (value[i] == 1 && temp[i] == 0)) && j == 0){
                    value[i] = 1;
                }
                else if(value[i] == 1 && temp[i] == 1 && j == 0){
                    value[i] = 0;
                    j = 1;
                }
                else if(value[i] == 0 && temp[i] == 0 && j == 1){
                    value[i] = 1;
                    j = 0;
                }
                else if(((value[i] == 0 && temp[i] == 1) || (value[i] == 1 && temp[i] == 0)) && j == 1){
                    value[i] = 0;
                }
                else if(value[i] == 1 && temp[i] == 1 && j == 1){
                    value[i] = 1;
                }
            }
        }

        j = 0;
        for(i = 31; i >= 0; i--){
            checksum += value[i] * pow(2, j);
            j++;
        }

        if(flag == 4){
            printf("%2d bit checksum is %8lx for all %4d chars\n", checkSumSize, checksum, characterCount+1);
        }
        else if(flag == 1){
            printf("X\n");
            printf("%2d bit checksum is %8lx for all %4d chars\n", checkSumSize, checksum, characterCount+2);
        }
        else if(flag == 2){
            printf("XX\n");
            printf("%2d bit checksum is %8lx for all %4d chars\n", checkSumSize, checksum, characterCount+3);
        }
        else if(flag == 3){
            printf("XXX\n");
            printf("%2d bit checksum is %8lx for all %4d chars\n", checkSumSize, checksum, characterCount+4);
        }
    }
}
