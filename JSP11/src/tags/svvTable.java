package tags;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.io.*;
public class svvTable extends SimpleTagSupport {
    private StringWriter sw = new StringWriter();
    private int rows;

    public void setRows(String rows) {
        this.rows = Integer.parseInt(rows);
    }

    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        getJspBody().invoke(sw);
        String body = sw.toString();
        String[] col = body.split(",");
        String tag="<table style=\"border: 1px solid black; border-collapse:collapse;\">";
        for (int i = 0; i < rows; i++) {
            tag += "<tr><td>";
            tag+=col[i];
            tag+="</td></tr>";
        }
        tag+="</table>";
        out.println(tag);
    }
}
