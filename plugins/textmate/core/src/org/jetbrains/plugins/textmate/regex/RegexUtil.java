package org.jetbrains.plugins.textmate.regex;

import com.intellij.openapi.util.TextRange;
import org.jcodings.specific.NonStrictUTF8Encoding;
import org.jcodings.specific.UTF8Encoding;
import org.jetbrains.annotations.NotNull;

public class RegexUtil {
  private RegexUtil() {
  }

  public static int codePointOffsetByByteOffset(byte[] stringBytes, int byteOffset) {
    if (byteOffset <= 0) {
      return 0;
    }
    return NonStrictUTF8Encoding.INSTANCE.strLength(stringBytes, 0, byteOffset);
  }

  public static int byteOffsetByCharOffset(@NotNull CharSequence charSequence, int charOffset) {
    if (charOffset <= 0) {
      return 0;
    }
    int result = 0;
    int i = 0;
    while (i < charOffset) {
      result += UTF8Encoding.INSTANCE.codeToMbcLength(charSequence.charAt(i));
      i++;
    }
    return result;
  }

  @NotNull
  public static TextRange codePointsRangeByByteRange(byte[] bytes, @NotNull TextRange byteRange) {
    int startOffset = codePointOffsetByByteOffset(bytes, byteRange.getStartOffset());
    int endOffset = codePointOffsetByByteOffset(bytes, byteRange.getEndOffset());
    return TextRange.create(startOffset, endOffset);
  }
}
