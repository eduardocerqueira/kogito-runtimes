package org.kie.kogito.codegen.unit;

import org.drools.ruleunits.api.DataSource;
import org.drools.ruleunits.api.DataStore;
import org.drools.ruleunits.api.DataStream;
import org.drools.ruleunits.api.RuleUnitData;
import org.drools.ruleunits.api.conf.Clock;
import org.drools.ruleunits.api.conf.ClockType;
import org.drools.ruleunits.impl.datasources.EventListDataStream;
import org.kie.kogito.codegen.data.StockTick;
import org.kie.kogito.codegen.data.ValueDrop;

@Clock(ClockType.PSEUDO)
public class StockUnit implements RuleUnitData {

    private DataStream<StockTick> stockTicks;
    private DataStore<ValueDrop> valueDrops;

    public StockUnit() {
        this(EventListDataStream.create(), DataSource.createStore());
    }

    public StockUnit(DataStream<StockTick> stockTicks, DataStore<ValueDrop> valueDrops) {
        this.stockTicks = stockTicks;
        this.valueDrops = valueDrops;
    }

    public DataStream<StockTick> getStockTicks() {
        return stockTicks;
    }

    public DataStore<ValueDrop> getValueDrops() {
        return valueDrops;
    }
}
