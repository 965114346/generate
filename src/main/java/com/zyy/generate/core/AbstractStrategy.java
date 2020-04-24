package com.zyy.generate.core;

/**
 * @author yangyang
 */
public abstract class AbstractStrategy implements Generator {

    protected String name;

    protected IPathStrategy pathStrategy;

    public AbstractStrategy setPath(IPathStrategy pathStrategy) {
        this.pathStrategy = pathStrategy;
        return this;
    }

    @Override
    public String getPath() {
        return pathStrategy.getPath();
    }
}
