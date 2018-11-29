#include "Global.h"

//��filename�ļ� �ṩ���������
int infile(fstream &in, string filename)
{
	in.open(filename, ios::in);
	if (!in)
	{
		cout << "�ļ�" << filename << "��ʧ��!" << endl;
		return 0;
	}
	return 1;
}

//��filename�ļ� �ṩ��������
int outfile(fstream &out, string filename)
{
	out.open(filename, ios::out);
	if (!out)
	{
		cout << "�ļ�" << filename << "��ʧ��!" << endl;
		return 0;
	}
	return 1;
}

//�ر���������ļ�
void closefile(fstream &file1, fstream &file2)
{
	file1.close();
	file2.close();
}

//����ļ�����
void readfile(string filename)
{
	char temp;
	fstream file;
	infile(file, filename);
	file >> noskipws;
	while (!file.eof())
	{
		file >> temp;
		cout << temp;
		if (file.fail())
			break;
	}
	cout << endl;
}

//����ָ���
void divideline()
{
	cout << "--------------------------------------------------------" << endl;
}

//�����̴����Լ��������
void precedure(string period, int num, string filein, string fileout, void(*func)(fstream &, fstream &), string content)
{

	cout << "�׶�" << period << "\t����:" << num << "\tĿ��:" << content << endl;

	cout << "\t����Դ�ļ�:\t\"" << filein << "\"" << endl;

	cout << "\tĿ������ļ�:\t\"" << fileout << "\"" << endl;
	if (infile(in, filein) && outfile(out, fileout))//��������ļ�������ļ������ڣ����ܳɹ��򿪣���ִ����Ӧ���ļ���д����
		func(in, out);
	else
		exit(0);
	closefile(in, out);
	cout << "ִ�гɹ�!" << endl;
	divideline();
}

//ȥ���ַ���ǰ�����Ŀո�
void fix(string &str)
{
	if (str[0] == ' ')
		str.assign(str, 1, str.length() - 1);		//���ַ�Ϊ�ո�
	if (str[str.length() - 1] == ' ')
		str.assign(str, 0, str.length() - 1);		//ĩ�ַ�Ϊ�ո�
}

