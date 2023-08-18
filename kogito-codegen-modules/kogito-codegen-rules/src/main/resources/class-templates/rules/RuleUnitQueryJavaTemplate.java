package com.myspace.demo;

import org.drools.ruleunits.api.RuleUnitInstance;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class $unit$Query$name$ {

    public static List<$ReturnType$> execute(RuleUnitInstance<$UnitType$> instance) {
        return instance.executeQuery( "$queryName$" ).toList().stream().map($unit$Query$name$::toResult).collect(toList());
    }

    private static $ReturnType$ toResult(Map<String, Object> tuple) {
        return ($ReturnType$) tuple.get("");
    }
}
