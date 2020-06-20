package tags;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.io.*;
public class svvSubmit extends SimpleTagSupport {


    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        String style1 = "style=\"color:white; background-color:royalblue; width: 100px; height:35px;\"";
        String style2 = "style=\"color:white; background-color:springgreen; width: 100px; height:35px;\"";
        String tag = "<div style=\"display:flex; flex-direction:column;\">" +
                "<input type=\"submit\" "+style1+" value = \"Log in\" name=\"case\"/>"+
                "<input type=\"submit\" "+style2+" value = \"Sign up\" name=\"case\"/>"+
                "</div>";
        out.println(tag);
    }
}
