#include "Global.h"

int main()
{
	cout << "当前处理程序:" << "保存在 \"" << resource << "\"" << endl;
	cout << "当前执行日志:" << "保存在 \"" << file_log << "\"" << endl;
	divideline();
	//预处理 
	//precedure函数：global.cpp:各过程处理及控制台界面输出程序
	//precedure(阶段编号，步骤编号，读入文件，目的输出文件，执行的功能，目标编号)
	//P1 1.删除注释 2.删除多余空白字符 
	precedure(period[1], 1, resource, file_nozs, delzs, content1[1]);//delza:删除注释
	precedure(period[1], 2, file_nozs, file_clear, delblank, content1[2]);//delblank:删除空白字符
	//P1 语法分析 3.将语法根据|分离  4.求出first,follow集
	precedure(period[1], 3, file_grammar, file_ll1grammar, delleftrecursion, content1[3]);//delleftrecursion：删除语法中的左递归及其公共因子
	precedure(period[1], 4, file_ll1grammar, file_ad_grammar, splitgrammar, content1[4]);//splitgrammar：以|为分割，拆分语法
	precedure(period[1], 5, file_ad_grammar, file_vn_vt, cal_vn_vt, content1[5]);//cal_vn_vt：求终结符和 非终结符
	precedure(period[1], 6, file_ad_grammar, file_ff, cal_first_follow, content1[6]); //cal_first_follow：求first集和follow集
	precedure(period[1], 7, file_ad_grammar, file_mtable, cal_mtable, content1[7]);//cal_mtable：求预测分析表
	//词法分析
	precedure(period[2], 1, file_clear, file_lex, lexicalanalysis, content2);//lexicalanalysis：词法分析
	fixlex();
	//语法/语义分析
	precedure(period[3], 1, file_lex, file_showstack, make_tree, content3[1]);//make_tree：构造语法分析树
	precedure(period[3], 2, file_lex, file_symtable, calsymboltable, content3[2]);//calsymboltable：生成符号表
	//中间代码生成/优化
	precedure(period[4], 1, file_lex, file_midcode, midcode, content4[1]);//midcode：生成中间代码
	precedure(period[4], 2, file_midcode, file_optimize, optimize, content4[2]);//optimize：中间代码优化
	system("pause");
	return 0;
}