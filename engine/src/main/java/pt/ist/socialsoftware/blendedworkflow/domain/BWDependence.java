package pt.ist.socialsoftware.blendedworkflow.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.socialsoftware.blendedworkflow.service.BWErrorType;
import pt.ist.socialsoftware.blendedworkflow.service.BWException;

public class BWDependence extends BWDependence_Base {
    private static Logger log = LoggerFactory.getLogger(BWDependence.class);

    public BWDependence(BWDataModel dataModel, BWProduct product,
            String value) {
        setDataModel(dataModel);
        setProduct(product);
        setPath(value);
    }

    public void check() {
        log.debug("check {}", getPath());

        List<String> pathLeft = Arrays.stream(getPath().split("\\."))
                .collect(Collectors.toList());
        if (pathLeft.size() == 0) {
            throw new BWException(BWErrorType.INVALID_PATH, getPath());
        }

        getProduct().getEntity().getNext(pathLeft, getPath());
    }

    public void delete() {
        setDataModel(null);
        setProduct(null);

        deleteDomainObject();
    }

}
