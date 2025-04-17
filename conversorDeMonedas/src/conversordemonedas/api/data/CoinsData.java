package conversordemonedas.api.data;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public record CoinsData(
    @SerializedName("supported_codes")
    ArrayList<ArrayList<String>> supportedCodes
) {}
