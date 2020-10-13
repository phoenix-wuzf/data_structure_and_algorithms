#include <stdio.h>

int main ()
{
    union {
        int i;
        char tmp;
    } test_u;

    test_u.i = 1;
    if (test_u.tmp == 1) {
        printf("little_endian: %d \n", test_u.tmp);
    } else {
        printf("big_endian: %d \n", test_u.tmp);
    }

    return 0;
}
