#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct bsTree
{
	char *name;
	struct bsTree *rightChild;
	struct bsTree *leftChild;
};

struct bsTree* createNode(char val[]);
struct bsTree* deleteName(struct bsTree *tree, char *name);
struct bsTree* insertIntoTree(struct bsTree *tree, char *name);
FILE* inOrder(struct bsTree *tree, FILE* fp);
struct bsTree* leftNode(struct bsTree* tree);
FILE* postOrder(struct bsTree *tree, FILE* fp);
FILE* preOrder(struct bsTree *tree, FILE* fp);
int searchForName(struct bsTree* tree, char* name, int index, int *flag);
FILE* writeToFileDeletes(FILE* fp, char* name, struct bsTree* temp);
FILE* writeToFileOrderings(struct bsTree *tree, FILE* ptr);
FILE* writeToFileSearching(FILE* fp, char* name, int temp);

struct bsTree* createNode(char *val)
{
	struct bsTree *temp = (struct bsTree*)malloc(sizeof(struct bsTree));
	temp->name = val;
	temp->rightChild = NULL;
	temp->leftChild = NULL;
	return temp;
}

struct bsTree* deleteName(struct bsTree *tree, char *name)
{
    if (tree == NULL)
  	{
		return tree;
  	}

    if (strcmp(tree->name, name) >= 1)
	{
	  	tree->leftChild = deleteName(tree->leftChild, name);
  	}
    else if (strcmp(tree->name, name) <= -1)
	{
      	tree->rightChild = deleteName(tree->rightChild, name);
  	}
    else
    {
        if (tree->leftChild == NULL)
        {
            struct bsTree *leaf = tree->rightChild;
            free(tree);
            return leaf;
        }
        else if (tree->rightChild == NULL)
        {
            struct bsTree *leaf = tree->leftChild;
            free(tree);
            return leaf;
        }
        struct bsTree *left = leftNode(tree->rightChild);
        tree->name = left->name;
        tree->rightChild = deleteName(tree->rightChild, left->name);
    }
    return tree;
}

struct bsTree* insertIntoTree(struct bsTree *tree, char *name)
{
	if(tree == NULL)
	{
		return createNode(name);
	}
	int result = strcmp(tree->name, name);
	if(result >= 1)
	{
		tree->leftChild = insertIntoTree(tree->leftChild, name);
	}
	else if(result <= -1)
	{
		tree->rightChild = insertIntoTree(tree->rightChild, name);
	}
	return tree;
}

FILE* inOrder(struct bsTree *tree, FILE* fp)
{
	if(tree != NULL)
	{
		inOrder(tree->leftChild, fp);
		fprintf(fp, "%s ", tree->name);
		inOrder(tree->rightChild, fp);
	}
	return fp;
}

struct bsTree* leftNode(struct bsTree *tree)
{
		struct bsTree *temp = tree;
		while(temp != NULL && temp->leftChild != NULL){
			temp = temp->leftChild;
		}
		return temp;
}

FILE* postOrder(struct bsTree *tree, FILE* fp)
{
	if(tree != NULL)
	{
		postOrder(tree->leftChild, fp);
		postOrder(tree->rightChild, fp);
		fprintf(fp, "%s ", tree->name);
	}
	return fp;
}

FILE* preOrder(struct bsTree *tree, FILE* fp)
{
	if(tree != NULL)
	{
		fprintf(fp, "%s ", tree->name);
		preOrder(tree->leftChild, fp);
		preOrder(tree->rightChild, fp);
	}
	return fp;
}

int searchForName(struct bsTree *tree, char *name, int index, int *flag)
{
    if(tree != NULL && *flag != 1){
        index = searchForName(tree->leftChild, name, index, flag);
		if(strcmp(tree->name, name) == 0){
			*flag = 1;
		}
		else if(*flag != 1){
			index++;
		}
        index = searchForName(tree->rightChild, name, index, flag);
    }
    return index;
}

FILE* writeToFileDeletes(FILE *fp, char *name, struct bsTree *temp)
{
    if(temp == NULL){
		fprintf(fp, "%s: Could not be deleted as they don't exist.\n", name);
		return fp;
	}
	fprintf(fp, "%s: deleted\n", name);
	return fp;
}

FILE* writeToFileOrderings(struct bsTree *tree, FILE *openPTR)
{
	if(openPTR == NULL)
	{
		printf("out.txt does not exist....\n\n");
		exit(1);
	}
	fprintf(openPTR, "Pre Order ");
	openPTR = preOrder(tree, openPTR);
	fprintf(openPTR, "\n");
	fprintf(openPTR, "In Order ");
	openPTR = inOrder(tree, openPTR);
	fprintf(openPTR, "\n");
	fprintf(openPTR, "Post Order ");
	openPTR = postOrder(tree, openPTR);
	return openPTR;
}

FILE* writeToFileSearching(FILE *fp, char *name, int temp)
{
    if(temp == -1){
		fprintf(fp, "%s: Not found, Items before: %d\n", name, temp+1);
		return fp;
	}
	fprintf(fp, "%s: Found, Items before: %d\n", name, temp);
	return fp;
}

int main(void)
{
	int numOfWords, numOfSearchWords, numOfDeleteWords;
	int i = 0, len;
	FILE *ptr = fopen("IO/nameIn.txt", "r");
	FILE *outptr = fopen("IO/nameOut.txt", "w");
	if(ptr == NULL)
	{
		printf("File doesnt exist....\n\n");
		exit(1);
	}
	fscanf(ptr, "%d", &numOfWords);
	fscanf(ptr, "%d", &numOfSearchWords);
	fscanf(ptr, "%d", &numOfDeleteWords);
	struct bsTree *tree = NULL;

	while(i < numOfWords)
	{
		char *name = malloc(sizeof(char));
		fscanf(ptr, "%s", name);
		tree = insertIntoTree(tree, name);
		i++;
	}
	outptr = writeToFileOrderings(tree, outptr);
	fprintf(outptr, "\nSearch Phase:\n");

	len = i;

	for(i = 0; i < numOfSearchWords; i++)
	{
		char *name = malloc(sizeof(char));
        int val, flag = 0;
        fscanf(ptr, "%s", name);
        val = searchForName(tree, name, 0, &flag);
		if(val == len){
			val = -1;
		}
		outptr = writeToFileSearching(outptr, name, val);
	}
	fprintf(outptr, "Delete Phase:\n");
	
	for(i = 0; i < numOfDeleteWords; i++)
	{
		char *name = malloc(sizeof(char));
		fscanf(ptr, "%s", name);
		struct bsTree *temp = (struct bsTree*)malloc(sizeof(struct bsTree));
		temp = deleteName(tree, name);
		outptr = writeToFileDeletes(outptr, name, temp);
	}
	outptr = writeToFileOrderings(tree, outptr);
	fclose(ptr);
	fclose(outptr);
	return 0;
}