# Comparaison librairies sérialisation XML JSON #

## Introduction ##

Les critères de comparaison sont :
  * l'extensibilité
  * le mode de mapping, interne (annotation), externe (schéma)
  * la simplicité d'utilisation
  * la performance
  * le mapping de type (Date, Collection, Map, Locale...)

Le test consiste à dé-sérialiser puis re-sérialiser un flux de données :

**XML**

```
<?xml version="1.0" encoding="UTF-8" ?>
<document locale="fr">
    <int-p>5</int-p>
    <integer>10</integer>
    <string>string</string>
    <myList>
        <element id="1">
            <text>me</text>
        </element>
        <element id="2">
            <text>you</text>
        </element>
    </myList>
    <myMap>
        <entry key="key1" value="value1" />
        <entry key="key2" value="value2" />
    </myMap>
</document>
```

**JSON**

```
{
    "locale":"fr",
    "int-p":5,
    "integer":10,
    "string":"string",
    "myList":[
        {"id":"1","text":"me"},
        {"id":"2","text":"you"}
    ],
    "myMap": {
        "key1":"value1",
        "key2":"value2"
    }
}
```

## Librairies ##

  * Jibx (xml) : http://jibx.sourceforge.net
  * Simple (xml) : http://simple.sourceforge.net
  * XStream (xml) : http://xstream.codehaus.org
  * JaxB (xml,json) : http://jaxb.java.net/
  * Jackson (json,xml) : http://jackson.codehaus.org
  * Gson (json) : http://code.google.com/p/google-gson

Et aussi
  * Castor (xml,xsd) : http://castor.codehaus.org
  * XmlBeans (xml,xsd) : http://xmlbeans.apache.org/
  * Protobuf (binaire,xml,json) : http://code.google.com/p/protobuf
  * SnakeYaml (yaml) : http://code.google.com/p/snakeyaml/

|           | **Jibx** | **Simple** | **XStream** | **JaxB**  | **Jackson** | **Gson** |
|:----------|:---------|:-----------|:------------|:----------|:------------|:---------|
| Type      |  XML   |   XML    |   XML     |   XML   |    JSON   |  JSON  |
| Interface |  +++   |   +++    |   +++     |   -     |    :-D    |  +++   |
| Perf      |  +++   |   +      |   ++      |   ++    |    +++    |  ++    |
| Extend    |   -    |   +++    |   +       |   ++    |    +      |  +++   |
| List      |  +++   |   +++    |   +++     |   +++   |    +++    |  +++   |
| Map       |   +    |   +++    |    -      |    -    |    +++    |  +++   |
| Date      |
| Locale    |
| Winner    |  10    |   13     |   9       |   7     |    10     |  14    |

### XML ###

#### Jibx ####

Type de mapping : fichier de configuration et compilation par injection de bytecode.

Les plus :
  * Le plus performant
  * Configuration externe aux objets
  * Plusieurs mappings possibles

Les moins :
  * Nécessite une pré-compilation
  * Pas aussi simple que des annotations
  * !! Pas de support d'extension simple

#### XStream ####

Type de mapping : annotations

Les plus :
  * Le plus performant après jibx
  * Très simple

Les moins :
  * Nécessite l'usage d'annotations dans les objets
  * !! Pas vraiment de support d'extension simple http://jira.codehaus.org/browse/XSTR-30

```
new XStream() {
    @Override
    protected MapperWrapper wrapMapper(MapperWrapper next) {
        return new MapperWrapper(next) {
            @Override
            public boolean shouldSerializeMember(Class definedIn, String fieldName) {
                if (definedIn == Object.class) { return false; }
                return super.shouldSerializeMember(definedIn, fieldName);
            }
        };
    }
};
```
_Merci Flo ;-)_

#### Simple ####

Type de mapping : annotations

Les plus :
  * Très simple
  * !! Support d'extension

Les moins :
  * Nécessite l'usage d'annotations dans les objets

Extensibilité

  * `@Version(revision=1.0) private double version;`

#### Jaxb ####

Type de mapping : annotations

Les plus :
  * C'est un standard 8-)
  * !! Support d'extension (par défaut)

Les moins :
  * Ne marche jamais du premier coup
  * Fonctionne très mal ou pas quand les properties des objets implémentent des interfaces ou héritent d'objets. m(
  * Nécessite l'usage d'annotations dans les objets

### JSON ###

#### Jackson ####

Type de mapping : annotations ou configuration par code

Les plus :
  * Le plus performant
  * Accepte le mapping d'objets tiers
  * !! Support d'extension

Les moins :
  * Documentation un peu confuse

Extensibilité

  * `@JsonIgnoreProperties(ignoreUnknown = true)`
  * `this.mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);`

#### Gson ####

Type de mapping : annotations ou configuration par code

Les plus :
  * Le plus performant après Jackson
  * Très simple
  * !! Support d'extension (par défaut)
  * Accepte le mapping d'objets tiers
  * Gestion du versionning du modèle
  * Utilisé et réalisé par Google :-p

Les moins :
  * J'en connais pas

### Autres Librairies ###

  * XML
    * protostuff : orienté server-server et pas facilement customisable
      * **Extensibilité** : support natif ;-)
    * xmlbeans : requière un schéma XML, 100% XSD
      * **Extensibilité** : XSD any
    * castor : battu par jibx car plus simple et beaucoup plus performant (mais castor implemente aussi JDO). Pas vraiment testé, si certains connaissent ?
      * **Extensibilité** : Unmarshaller.setIgnoreExtraElements(true);
  * JSON
    * protostuff : orienté server-server et pas facilement customisable
      * **Extensibilité** : support natif ;-)
    * json-flexjson : performances déplorables
    * json-simple : low level api
    * jettison : low level api

## Mode de sérialisation ##

| | Avantages | Inconvénients |
|:|:----------|:---------------|
| Interne (annotation) | facile à mettre en place | difficulté à maintenir la compatibilité de la sérialisation en cas de changement du modèle interne |
| Externe (schéma)     | facile de maintenir la compatibilité de la sérialisation en cas de changement du modèle interne | il faut définir le schéma et rendre accessible l'API de message aux applications |

## Extensibilité ##

L'extensibilité doit permettre l'évolution des données en fournissant une rétro-compatibilité transparent.

Concrètement, si on ajoute un attribut ou un élément au XML généré par le serveur, on ne doit pas avoir à changer la partie cliente.

## Performances ##

Temps moyen de dé-sérialisation et sérialisation sur 1000 et 100 000 itérations avec données aléatoires

![http://wiki.caffeine.googlecode.com/git/performance-serialization-xml-json.png](http://wiki.caffeine.googlecode.com/git/performance-serialization-xml-json.png)

## Conclusion ##

Pour la sérialisation XML, le gagnant en terme de performances est Jibx mais il n'est pas simple d'écrire le mapping et il n'offre aucun support pour l'extensibilité. Simple est comme son nom l'indique le plus simple et offre un bon mapping ainsi qu'un support pour l'extensibilité. XStream est un peu plus performant mais à besoin d'être tweaké pour faire aussi bien que Simple.

Pour la sérialisation JSON, le gagnant est Jackson avec Gson qui le colle de très près. Ils supportent aussi bien le mapping de différents objets que l'extensibilité. Jackson est un peu plus performant mais Gson est vraiment très simple et la doc est très claire.

Si la communication est exclusivement de serveur à serveur, l'utilisation de la sérialisation Java ou mieux de protobuf est fortement recommandée pour des raisons de performances et d'extensibilité.

Si la communication est à destination de clients web, une sérialisation XML JSON est recommandée car simple et accessible.

## References ##

https://github.com/eishay/jvm-serializers/wiki/

http://blog.xebia.fr/2010/08/18/comparatif-des-librairies-json/

http://code.google.com/p/json-smart/wiki/Benchmark

http://www.igvita.com/2011/08/01/protocol-buffers-avro-thrift-messagepack/