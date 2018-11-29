#pragma once
#include "Include_Package.h"

static string resource = "R0_Project.c";//������Դ����
static string file_grammar = "R1_RG.data";	//Դ�ķ�(Resource Grammmer)�ļ�,BNF�ķ�
static string file_mapper = "R2_WT.data";	//�����ֱ�ӳ���(Word type)

static string file_ll1grammar = "g1_ELR.data";	//������ݹ�(Eliminate left recursion)������
static string file_ad_grammar = "g2_EG.data";	//�ع��ķ�(Extension grammar)
static string file_vn_vt = "g3_VNVT.data";	//�ս��,���ս���洢�ļ�
static string file_ff = "g4_FF.data";	//first,follow���洢�ļ�
static string file_mtable = "g5_FAT.data";		//Ԥ�������(Forecast analysis table)�洢�ļ�

static string file_nozs = "c1_EC.data";	//ȥע�ͺ�(Eliminate comments)���ļ�
static string file_clear = "c2_EB.data";	//�����ո���հ��ַ�(Eliminate blanks)
static string file_lex = "c3_LA.data";		//�ʷ�����(lexical analysis)����洢�ļ�
static string file_showstack = "c4_GA.data";	//�﷨����(Gramma analysis)���﷨����ջ����Ϣ���
static string file_symtable = "c5_ST.data";	//���ű�(Symbol table)�洢�ļ�
static string file_midcode = "c6_IC.data";		//�м����(Intermediate code)���
static string file_optimize = "c7_ICO.data";	//�м�����Ż�(Intermediate code optimization)
static string file_log = "log.txt";          //������־

static string period[7] = { "",//ִ�н׶����ƣ��ܲ��������б�
							"1:Ԥ����/��ʼ��",
							"2:�ʷ�����     ",
							"3:�﷨/�������",
							"4:�м�������� "
};
static string content1[10] = { "",//���׶θ������Ŀ��������ƣ��ֲ������б�
							  "ȥ��ע��",
							  "ȥ���հ��ַ�",
							  "������ݹ��Լ�����������",
							  "���﷨����|���",
							  "��vn,vt����",
							  "��fisrt,follow��",
							  "����Ԥ�������mtable"
};
static string content2 = "�ʷ�����";
static string content3[10] = { "",
							  "�����﷨������",
							  "���ɷ��ű�",
};
static string content4[3] = { "",
							 "�м��������",
							 "�м�����Ż�"
};
static string call = "call ";
static string param = "param ";
static string proc = "Prog ";
static int maxline;
static const int maxsize = 255;
static const string firststr = "S";

static fstream in, out;

static map<string, string> vttype;	//�ս�����ͱ�
static vector<string> vn;			//���ս������
static vector<string> vt;			//�ս������

//����Ҫʹ�õ��Ĵ洢��ָ��
static vector<string>::iterator iter;
static map<string, string>::iterator iter_map;
static map<string, vector<string>>::iterator maper;
static map<string, int>::iterator map_si;
static map<int, vector<string>> grammarmap;		//�洢�﷨���Ƶ�
static map<int, vector<string>>::iterator gmaper;
static map<string, vector<string>> firstlist;		//first��
static map<string, vector<string>> followlist;		//follow��
static map<string, int> emptymake;			//�洢���з��ս�����Ƶ������ֵ��Ƶ�ʽ���

//token�ṹ��
typedef struct Token
{
	string re;			//tokenԭʼ�ַ���
	string type;		//token����
	int line;			//token������
	int colume;			//token������
}Token;

typedef struct info
{
	int arrayflag;		//��¼�����Ƿ�Ϊ����
	int emptyflag;		//��¼�÷��ս���Ƿ��Ƴ���
	int whilenum1;		//��¼while��ڵ�����ַ����ֵ
	int whilenum2;		//��¼while���ڵ�����ַ����ֵ
	int ifnum1;			//��¼if������������ַ����ֵ
	int ifnum2;			//��¼if���ٳ�������ַ����ֵ
	int elsenum1;		//��¼else�������ַ����ֵ
	int elsenum2;		//��¼else��������ַ����ֵ
	int returnflag;
}info;

//�����ṹ��
typedef struct Gtree
{
	char re[255];		//���Ԫ��ԭ�ַ���
	char type[255];		//���Ԫ������	���ս��Ϊderivation �ս��Ϊ�������� ���ڵ�Ϊhead 
	int bitnum;			//��㺢�ӽڵ����
	int mtnum;			//��Ϊ���ս�� ��¼�Ƶ�ʽ���
	int nodenum;
	struct Gtree * sons[255];		//���ӽڵ��ַ����
	struct Gtree *brother;			//�ֵܽڵ��ַ
	struct Gtree *father;			//���׽ڵ��ַ
	struct info *info;				//������Ϣ
}treebit, *bit;

//ջ�ṹ��
typedef struct Stack
{
	vector<string> st;		//ջ
	int current;			//��ǰջ��Ԫ�ظ���
}st;

//Ԥ�������ṹ��
typedef struct Mtable
{
	map<string, int>vnname;		//���ս����
	map<string, int>vtname;		//�ս����
	int mtable[maxsize][maxsize];		//Ԥ�������
	int vnnum;		//��¼���ս������
	int vtnum;		//��¼�ս������
}mt;

//��ָ���ļ�����/��� ����1��ʾ�򿪳ɹ�������0��ʾ��ʧ��
int infile(fstream &in, string filenmae);
int outfile(fstream &out, string filenmae);

//�ر�in,out������������������ļ�
void closefile(fstream &file1, fstream &file2);

//����ļ�����
void readfile(string filename);

//�ָ���
void divideline(void);

//�����̴����Լ��������
void precedure(string period, int num, string filein, string fileout, void(*func)(fstream &, fstream &), string content);

//ȥ���ַ���ǰ�����Ŀո�
void fix(string &str);