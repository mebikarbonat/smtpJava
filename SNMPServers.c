#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <string.h>
#include <sys/types.h>
#include <time.h> 
#define MAX 80

int main(int argc, char *argv[])
{
    char buff[MAX];
    int valread;
    int listenfd = 0, connfd = 0;
    struct sockaddr_in serv_addr; 
    
    FILE *file;
    char ch;

    char *hello = "EMPTY";
    
    char buffer[1024] = {0};
    char sendBuff[1025];
    time_t ticks; 

    listenfd = socket(AF_INET, SOCK_STREAM, 0);
    memset(&serv_addr, '0', sizeof(serv_addr));
    memset(sendBuff, '0', sizeof(sendBuff)); 

    serv_addr.sin_family = AF_INET;
    serv_addr.sin_addr.s_addr = htonl(INADDR_ANY);
    serv_addr.sin_port = htons(1616); 

    bind(listenfd, (struct sockaddr*)&serv_addr, sizeof(serv_addr)); 

    listen(listenfd, 10); 
    bzero(buff, MAX);
    connfd = accept(listenfd, (struct sockaddr*)NULL, NULL); 
    printf("Connected! with SNMP Client:\n");
    //valread = read( connfd , buffer, 1024);
    read( connfd , buff, sizeof(buff));    
    printf("%s\n ", buff);
    bzero(buff, MAX);

    read( connfd , buff, sizeof(buff));
    //printf("%s\n ", buff);
    //printf("%s\n ", buff);
    
    if (strstr(buff, "Walk") != NULL) 
    {
     printf("Client is querying!\n");
     
     if (strstr(buff, "1.3.6.1.2.1.25.3.3.1.2") != NULL)
     {
       printf("Client:%s\n", buff);
       //hello = "Server CPU: \n";
    
       //file = popen("sysctl hw.model hw.machine hw.ncpu | grep model","r");
       //ch=fgetc(file);
       //putchar(ch);
       //printf("%s\n", ch);
       //hello = ch;
     }     

    }
    else
    {
     printf("Unknown Command!\n");
    }

    send(connfd , hello , strlen(hello) , 0 );
    printf("SNMP query responded!\n");
    close(connfd);
    pclose(file);
    sleep(1);
}
