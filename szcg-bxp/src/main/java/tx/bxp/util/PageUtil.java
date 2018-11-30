package tx.bxp.util;

/**
        * @program: szcg-project
        * @description: 分页工具
        * @author:pck
        * @create: 2018-07-31 09:11
        **/
public class PageUtil {

    public static Integer[] paging(String Page,String PageSize){
        Integer [] i=new Integer[2];
        i[0]=(Integer.valueOf(Page)-1) *Integer.valueOf(PageSize);
        i[1]=Integer.valueOf(PageSize);
        return i;
    }

}
