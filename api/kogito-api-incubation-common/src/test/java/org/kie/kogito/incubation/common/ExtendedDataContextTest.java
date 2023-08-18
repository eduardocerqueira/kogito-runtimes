package org.kie.kogito.incubation.common;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ExtendedDataContextTest {

    @Test
    public void convertReturnsDataSection() {
        User data = new User();
        data.firstName = "Paul";
        data.lastName = "McCartney";
        data.addr = new Address();
        data.addr.street = "Abbey Rd.";

        MapDataContext meta = MapDataContext.create();
        meta.set("meta-value", "this is not data");

        ExtendedDataContext ctx = ExtendedDataContext.of(meta, data);

        assertThat(ctx.as(MapDataContext.class)).as("Converting an ExtendedContext should be equivalent to converting its data section").isEqualTo(ctx.data().as(MapDataContext.class));

    }

    @Test
    public void convertToExtendedIsNoOp() {
        User data = new User();
        data.firstName = "Paul";
        data.lastName = "McCartney";
        data.addr = new Address();
        data.addr.street = "Abbey Rd.";

        MapDataContext meta = MapDataContext.create();
        meta.set("meta-value", "this is not data");

        ExtendedDataContext ctx = ExtendedDataContext.of(meta, data);

        assertThat(ctx.as(DataContext.class)).isSameAs(ctx);
        assertThat(ctx.as(ExtendedDataContext.class)).isSameAs(ctx);

        assertThat(ctx.as(MapDataContext.class)).as("Converting an ExtendedContext should be equivalent to converting its data section").isEqualTo(ctx.data().as(MapDataContext.class));

    }

    @Test
    public void convertDataContextToExtendedWraps() {
        User data = new User();
        data.firstName = "Paul";
        data.lastName = "McCartney";
        data.addr = new Address();
        data.addr.street = "Abbey Rd.";

        ExtendedDataContext edc = data.as(ExtendedDataContext.class);
        assertThat(edc.data()).isEqualTo(data);
    }

    @Test
    public void convertMapDataContextToExtendedWraps() {
        User data = new User();
        data.firstName = "Paul";
        data.lastName = "McCartney";
        data.addr = new Address();
        data.addr.street = "Abbey Rd.";

        MapDataContext mapData = data.as(MapDataContext.class);
        ExtendedDataContext edc = mapData.as(ExtendedDataContext.class);
        assertThat(edc.data()).isEqualTo(mapData);
    }

    @Test
    public void convertMetaToMap() {
        User data = new User();
        data.firstName = "Paul";
        data.lastName = "McCartney";
        data.addr = new Address();
        data.addr.street = "Abbey Rd.";

        MapDataContext meta = MapDataContext.create();
        meta.set("meta-value", "this is not data");

        ExtendedDataContext ctx = ExtendedDataContext.of(meta, data);

        assertThat(ctx.meta()).isEqualTo(meta);

        MapDataContext fromMeta = MapDataContext.from(ctx.meta());
        assertThat(fromMeta).isEqualTo(meta);

    }

    @Test
    public void convertCustomMetaToMap() {
        User data = new User();
        data.firstName = "Paul";
        data.lastName = "McCartney";
        data.addr = new Address();
        data.addr.street = "Abbey Rd.";

        CustomMeta meta = new CustomMeta();
        meta.value = "this is not data";

        ExtendedDataContext ctx = ExtendedDataContext.of(meta, data);

        assertThat(ctx.meta()).isEqualTo(meta);

        MapDataContext fromMeta = MapDataContext.from(ctx.meta());
        assertThat(fromMeta.get("value")).isEqualTo(meta.value);
    }

}
