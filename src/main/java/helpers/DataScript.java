package helpers;

import java.text.Normalizer;

public class DataScript {

    public static String transformText(String text) {
        if (text == null) {
            return "";
        }

        // Chuyển đổi văn bản thành chữ in hoa, loại bỏ dấu, và loại bỏ ký tự không phải chữ cái hoặc số
        return Normalizer.normalize(text.toUpperCase(), Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "") // Loại bỏ dấu
                .replaceAll("[^A-Z0-9]", ""); // Loại bỏ các ký tự không phải chữ cái hoặc số
    }

    // method update String
  

}
