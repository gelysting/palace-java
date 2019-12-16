package cn.knight.fly.test.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.util.List;
import static java.lang.Thread.sleep;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Package cn.knight.fly.test.crawler
 * @Description
 * @Author Fly
 * @Date 2019-10-31 14:30
 * @Version V1.0
 */
public class crawler01 {

    private static final Logger logger = LoggerFactory.getLogger(crawler01.class);

    public static void doCrawer() {

        String url_base = "https://so.gushiwen.org/shiwen/";
        String url = "";
        /**
         * https://so.gushiwen.org/shiwen/default_0AA1.aspx
         * 只允许深度20
         *
         * https://so.gushiwen.org/shiwen/default_2Ab90660e3e492A1.aspx
         * 只允许深度10
         */
        for (int q = 0; q < 20; q++) {
            url = url_base + "default_0AA" + String.valueOf(q + 1) + ".aspx";
            System.out.println("==========>>>>>"+url);

            try {

                Document doc = Jsoup.connect(url)
                        //.data("query", "Java")
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36")
                        //.cookie("auth", "token")
                        .timeout(3000)
                        .post()
                        //.get()
                        ;
                //System.err.println(doc);  //打印获取的网页

                Element body = doc.body();
                //System.err.println(body);   //打印获取的网页的body块

                Elements divs = null;
                Elements elementsCont = null;

                divs = body.select("div.main3 > div.left > div.sons");
                //System.out.println(divs.first());
                /*----------------------------------------*/
                String title = null;
                String era = null;
                String author = null;
                String content = null;
                String label = null;

                List<TextNode> textNodeList = null;

                List<Node> nodeList = null;

                Element element_tmp = null;
                TextNode textNode_tmp = null;
                for (Element div : divs) {
                    if (div.hasText()) {

                        //打印标题
                        title = div.select("div.cont > p:nth-child(2) > a > b").text();
                        //System.err.println(title);

                        //打印时代
                        era = div.select("div.cont > p.source > a:nth-child(1)").text();
                        //System.err.println(era);

                        //打印作者
                        author = div.select("div.cont > p.source > a:nth-child(3)").text();
                        //System.err.println(author);

                        //打印作品主体-直接打印
                        //content = div.getElementsByClass("contson").text();
                        //System.err.println( content );

                        //打印作品主体-格式化打印
                        elementsCont = div.getElementsByClass("contson");
                        content = "";
                        for (Element ele : elementsCont) {
                            if (ele.hasText()) {

                                nodeList = ele.childNodes();
                                //System.err.println(nodeList.toString());

                                //因为此节点下存在两种情况，一个是元素本身的文字，一个是元素下的元素文字，因此需要按情况分析获取主体内容
                                for (int i = 0; i < nodeList.size(); i++) {
                                    if (nodeList.get(i).getClass() == Element.class) {
                                        element_tmp = (Element) nodeList.get(i);
                                        if (!element_tmp.text().trim().isEmpty()) {
                                            content += element_tmp.text().trim();
                                            //System.err.println(element_tmp.text().trim());
                                        }
                                    } else if (nodeList.get(i).getClass() == TextNode.class) {
                                        textNode_tmp = (TextNode) nodeList.get(i);
                                        if (!textNode_tmp.text().trim().isEmpty()) {
                                            content += textNode_tmp.text().trim();
                                            //System.err.println(textNode_tmp.text().trim());
                                        }
                                    }
                                }
                            }
                        }
                        //System.err.println("\n主体：" + content);

                        //打印标签
                        label = div.select("div.tag").text();
                        //System.err.println("标签：" + label);

                        //打印换行
                        //System.err.println("");

                        int ret = 0;
                        //ret = executeUpdate(era,author,title,content);
                        ret = TestSQL.executeProcedure(era, author, title, content);
                        if (ret != 1) {
                            System.err.println("更新失败！ret=" + ret);
                        } else {
                            System.err.println("更新成功！");
                        }
                    }
                }
                sleep(500); //控制不要访问速度太快，模拟手动浏览操作，避免被服务器识别为机器人操作
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void testJdbc() {
        JDBCUtil dao = new JDBCUtil();
        try {

            Object[] params = null;
            ResultSet resultSet = dao.executeQuery("Select * from book", params);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            //输出列名称
            int columnCount = resultSetMetaData.getColumnCount();
            for (int j = 1; j <= columnCount; j++) {
                System.out.print(resultSetMetaData.getColumnName(j) + "\t");
            }
            System.out.println("\n-----------------------");

            while (resultSet.next()) {
                for (int i = 0; i < columnCount; i++) {
                    String columnName = resultSetMetaData.getColumnName(i + 1);
                    Object columnValue = resultSet.getObject(i + 1);
                    System.out.print(columnName + "：" + columnValue.toString() + "\t");
                }
                System.out.print("\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dao.destroy();
        }
    }

    public static void main(String[] args) {

        //doCrawer();
        logger.info("输出INFO日志");
        //testJdbc();
        Clob clob = null;
    }
}
