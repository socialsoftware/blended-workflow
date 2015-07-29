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

    @Override
    public void setPath(String value) {
        checkPathPrefix(value);
        super.setPath(value);
    }

    public BWDependence(BWDataModel dataModel, BWProduct product,
            String value) {
        setDataModel(dataModel);
        setProduct(product);
        setPath(value);
    }

    private void checkPathPrefix(String value) {
        if (!value.split("\\.")[0].equals(getProduct().getEntity().getName())) {
            throw new BWException(BWErrorType.INVALID_PATH,
                    value + " requires to have the Entity name as prefix: "
                            + getProduct().getEntity().getName());
        }
    }

    public void check() {
        log.debug("check {}", getPath());

        checkPathPrefix(getPath());

        List<String> pathLeft = Arrays.stream(getPath().split("\\."))
                .collect(Collectors.toList());
        if (pathLeft.size() == 1) {
            throw new BWException(BWErrorType.INVALID_PATH, getPath());
        }

        pathLeft.remove(0);

        getProduct().getEntity().getNext(pathLeft, getPath());
    }

    public void delete() {
        setDataModel(null);
        setProduct(null);

        deleteDomainObject();
    }

}
