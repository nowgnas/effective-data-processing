package org.data.nowgnas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CachedData {
    private List<String> stringList;
    private Map<String, Object> objectMap;
}
