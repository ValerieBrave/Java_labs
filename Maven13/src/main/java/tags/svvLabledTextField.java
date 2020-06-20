package tags;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.io.*;
public class svvLabledTextField extends SimpleTagSupport {
    private String label;
    private String parname;
    public void setLabel(String label) {
        this.label = label;
    }

    public void setParname(String parname) {
        this.parname = parname;
    }

    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        String tag =
                "<div style=\"display:flex; flex-direction:column; background-color:orange; width: 100px; height: 40px;\">" +
                        label+
                        "<input type=\"text\" name=\""+this.parname+"\"/>"+
                        "</div>";
        out.println(tag);
    }
}
