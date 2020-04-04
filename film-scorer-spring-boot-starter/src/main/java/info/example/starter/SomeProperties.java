package info.example.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "some")
public class SomeProperties {
    private Integer mapper;

    public Integer getMapper() {
        return mapper;
    }

    public void setMapper(Integer mapper) {
        this.mapper = mapper;
    }
}
