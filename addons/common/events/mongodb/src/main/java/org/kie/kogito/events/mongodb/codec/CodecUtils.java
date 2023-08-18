package org.kie.kogito.events.mongodb.codec;

import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.DocumentCodec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.kie.kogito.event.AbstractDataEvent;

import com.mongodb.MongoClientSettings;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;

public class CodecUtils {

    private CodecUtils() {
    }

    static final String ID = "_id";

    private static final Codec<Document> CODEC = new DocumentCodec(CodecRegistries.fromRegistries(
            MongoClientSettings.getDefaultCodecRegistry(),
            fromProviders(PojoCodecProvider.builder().automatic(true).build())));

    static void encodeDataEvent(Document doc, AbstractDataEvent<?> event) {
        doc.put(ID, event.getId());
        doc.put("specversion", event.getSpecVersion().toString());
        doc.put("source", event.getSource().toString());
        doc.put("type", event.getType());
        doc.put("time", event.getTime());
        doc.put("subject", event.getSubject());
        doc.put("dataContentType", event.getDataContentType());
        doc.put("dataSchema", event.getDataSchema());
        doc.put("kogitoProcessinstanceId", event.getKogitoProcessInstanceId());
        doc.put("kogitoRootProcessinstanceId", event.getKogitoRootProcessInstanceId());
        doc.put("kogitoProcessId", event.getKogitoProcessId());
        doc.put("kogitoRootProcessId", event.getKogitoRootProcessId());
        doc.put("kogitoAddons", event.getKogitoAddons());
        doc.put("kogitoIdentity", event.getKogitoIdentity());
    }

    static Codec<Document> codec() {
        return CODEC;
    }
}
