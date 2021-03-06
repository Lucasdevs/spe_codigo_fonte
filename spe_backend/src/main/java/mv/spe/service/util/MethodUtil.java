package mv.spe.service.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

public final class MethodUtil {

    private MethodUtil() {
        throw new IllegalStateException(ConstantsUtil.CLASSE_NAO_INSTANCIAVEL);
    }

    public static Pageable removeCaseSort(Pageable pageable) {
        List<Sort.Order> orderList = pageable.getSort().get()
                .map(element -> new Sort.Order(element.getDirection(), element.getProperty()).ignoreCase())
                .collect(Collectors.toList());

        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(orderList));
    }
}
