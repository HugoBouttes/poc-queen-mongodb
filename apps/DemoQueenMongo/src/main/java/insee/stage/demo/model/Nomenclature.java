package insee.stage.demo.model;

import org.json.JSONObject;
import org.springframework.data.annotation.Id;

public class Nomenclature {
    @Id
    private String id;
    private String reg;
    private String cheflieu;
    private String libelle;
    private String tncc;
    private String nccenr;
    private String ncc;

    private JSONObject nomenclature;

    public Nomenclature(String id, String reg, String cheflieu, String libelle, String tncc, String nccenr, String ncc) {
        this.id = id;
        this.reg = reg;
        this.cheflieu = cheflieu;
        this.libelle = libelle;
        this.tncc = tncc;
        this.nccenr = nccenr;
        this.ncc = ncc;

        this.nomenclature.put("id",id);
        this.nomenclature.put("reg",reg);
        this.nomenclature.put("cheflieu",cheflieu);
        this.nomenclature.put("libelle",libelle);
        this.nomenclature.put("tncc",tncc);
        this.nomenclature.put("nccenr",nccenr);
        this.nomenclature.put("ncc",ncc);
    }
}
