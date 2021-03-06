package mv.spe.service.util;

public class ConstantsUtil {

    private ConstantsUtil() {
        throw new IllegalStateException(ConstantsUtil.CLASSE_NAO_INSTANCIAVEL);
    }

    public static final String CLASSE_NAO_INSTANCIAVEL = "Classe de Constantes não pode ser instanciada";
    public static final String ID_NULL = "O ID desse objeto não pode ser nulo";
    public static final String ID_NOT_NULL = "ID deve ser nulo";
    public static final String ID_NOT_FOUND = "ID não existe";
    public static final String DUPLICATE_PARAMETER = "Alguns parâmetros do objeto já existem";

}
