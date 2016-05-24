package com.ginsmile.calculatorpro;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by xujin on 16/04/29.
 */
public class Calculate {

    //使用后缀 表达式来计算原表达式的值，得到double类型的结果。
    public static String calculate(String exp) throws Exception {
        ArrayList<String> inOrderExp = getStringList(exp);  //String转换为List,得到中缀表达式
        ArrayList<String> postOrderExp = getPostOrder(inOrderExp);
        double res = calPostOrderExp(postOrderExp);
        if(res == Math.floor(res)) return (long)res+"";//当结果是整数的时候，输出不要加小数点
        return res+"";
    }

    //把数字和符号加入list
    private  static ArrayList<String> getStringList(String s) {
        ArrayList<String> res = new ArrayList<String>();
        String num = "";
        for(int i = 0; i < s.length(); i++){
            if(Character.isDigit(s.charAt(i))||(s.charAt(i) == '.')){
                num += s.charAt(i);
            }else{
                if(num != ""){
                    res.add(num);//把上一个数字加到list
                }
                res.add(s.charAt(i)+"");//把当前符号加到list
                num = "";
            }
        }
        //最后一个数字
        if(num != ""){
            res.add(num);
        }
        return res;
    }

    //将中缀表达式转化为后缀表达式
    private static ArrayList<String> getPostOrder(ArrayList<String> inOrderExp){
        ArrayList<String> postOrderExp = new ArrayList<String>();//储存结果
        Stack<String> operStack = new Stack<String>();//运算符栈

        for(int i = 0 ; i < inOrderExp.size(); i++){
            String cur = inOrderExp.get(i);
            if(isOper(cur)){
                while(!operStack.isEmpty() && compareOper(operStack.peek(),cur)){
                    //只要运算符栈不为空，并且栈顶符号优先级大与等于cur
                    postOrderExp.add(operStack.pop());
                }
                operStack.push(cur);
            }
            else {
                postOrderExp.add(cur);
            }
        }
        while(!operStack.isEmpty()){
            postOrderExp.add(operStack.pop());
        }
        return postOrderExp;
    }

    //比较两个运算符的大小，如果peek优先级大于等于cur，返回true
    private static boolean compareOper(String peek, String cur) {
        if("*".equals(peek) && ("/".equals(cur) || "*".equals(cur) ||"+".equals(cur) ||"-".equals(cur))){
            return true;
        }else if("/".equals(peek) && ("/".equals(cur) || "*".equals(cur) ||"+".equals(cur) ||"-".equals(cur))){
            return true;
        }else if("+".equals(peek) && ("+".equals(cur) || "-".equals(cur))){
            return true;
        }else if("-".equals(peek) && ("+".equals(cur) || "-".equals(cur))){
            return true;
        }
        return false;
    }

    //判断一个字符串是否是运算符，+-*/
    private static boolean isOper(String c){
        if(c.equals("+")||
                c.equals("-")||
                c.equals("*")||
                c.equals("/")) return true;
        return false;
    }

    //计算一个后缀表达式
    private static double calPostOrderExp(ArrayList<String> postOrderExp) throws Exception {
        Stack<String> stack = new Stack<String>();
        for(int i = 0 ; i < postOrderExp.size(); i++){
            String curString = postOrderExp.get(i);
            if(isOper(curString)){
                double a = Double.parseDouble(stack.pop());
                double b = Double.parseDouble(stack.pop());
                double res = 0.0;
                switch(curString.charAt(0)){
                    case '+':
                        res = b + a;
                        break;
                    case '-':
                        res = b - a;
                        break;
                    case '/':
                        if(a == 0) throw new Exception();
                        res = b / a;
                        break;
                    case '*':
                        res = b * a;
                        break;
                }
                stack.push(res+"");
            }else{
                stack.push(curString);
            }
        }
        return Double.parseDouble(stack.pop());
    }


}
