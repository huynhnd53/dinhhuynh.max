package GUI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ken
 */
public class TextEditor {

    private String data;
    private boolean textChanged;

    public boolean newFile(int result) {
        return result == 0;
    }

    //the function open a file text document;
    public String openFile(String fileName) {
        StringBuilder contents = new StringBuilder();
        String line;
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                contents.append(line).append(System.getProperty("line.separator"));
            }
            br.close();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
        return contents.toString();
    }

    //the function to save file 
    public void save(String fileName) {
        FileWriter fw;
        try {
            fw = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(data);
            bw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //the function to find text 
    public int findNext(String findText, int nextPos, boolean matchCase) {
        StringBuilder findContent = new StringBuilder();
        int index = 0;
        findContent.append(data.substring(nextPos, data.length()));
        if (matchCase) {
            index = findContent.indexOf(findText);
            if (nextPos + findText.length() > data.length() || index == -1) {
                return -1;
            }
        } else {
            index = findContent.toString().toLowerCase().indexOf(findText.toLowerCase());
            if (nextPos + findText.length() > data.length() || index == -1) {
                return -1;
            }
        }
        return index + nextPos;
    }

    public String replace(String replaceText, int startSelectPoint, int endSelectPoint) {
        StringBuilder result = new StringBuilder(data);
        if (startSelectPoint != endSelectPoint) {
            result.replace(startSelectPoint, endSelectPoint, replaceText);
        }
        return result.toString();
    }

    public String replaceAll(String replaceText, String textReplace, boolean matchCase) {
        String result = "";
        if (matchCase) {
            result = data.replaceAll(textReplace, replaceText);
        } else {
            Pattern p = Pattern.compile(textReplace, Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(data);
            result = m.replaceAll(replaceText);
        }
        return result;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isTextChanged() {
        return textChanged;
    }

    public void setTextChanged(boolean textChanged) {
        this.textChanged = textChanged;
    }
}
