package com.fanmo.dengpao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

/**
 * @author fanmo
 * @date 2019/04/21
 */
public class JsonUtilTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void emptyJsonPlatTest() {
        String jsonStr = null;
        Map<String, Object> map = JsonUtil.jsonFlat(jsonStr);

        Assert.assertEquals(0, map.size());

        jsonStr = "";
        map = JsonUtil.jsonFlat(jsonStr);
        Assert.assertEquals(0, map.size());

        jsonStr = "{}";
        map = JsonUtil.jsonFlat(jsonStr);
        Assert.assertEquals(0, map.size());
    }

    @Test
    public void simpleMapJsonPlatTest() {
        String jsonStr = "{\n" +
                "  \"index\" : \"test3\",\n" +
                "  \"shard\" : \"0\",\n" +
                "  \"st\" : 0,\n" +
                "  \"primary\" : \"false\",\n" +
                "  \"replica\" : true\n" +
                "}";
        Map<String, Object> map = JsonUtil.jsonFlat(jsonStr);

        Assert.assertEquals(5, map.size());
        Assert.assertEquals("test3", map.get("index"));
        Assert.assertEquals("0", map.get("shard"));
        Assert.assertEquals(0, map.get("st"));
        Assert.assertEquals("false", map.get("primary"));
        Assert.assertEquals(true, map.get("replica"));
    }

    @Test
    public void simpleListJsonPlatTest() {
        String jsonStr = "[\n" +
                "    {\n" +
                "        \"host\": \"172.17.15.205\",\n" +
                "        \"port\": 9300\n" +
                "    },\n" +
                "    {\n" +
                "        \"host\": \"172.17.15.206\",\n" +
                "        \"port\": 9300\n" +
                "    },\n" +
                "    {\n" +
                "        \"host\": \"172.17.15.207\",\n" +
                "        \"port\": 9300\n" +
                "    }\n" +
                "]";

        Map<String, Object> map = JsonUtil.jsonFlat(jsonStr);

        Assert.assertEquals(6, map.size());
        Assert.assertEquals("172.17.15.205", map.get("[0].host"));
        Assert.assertEquals("172.17.15.206", map.get("[1].host"));
        Assert.assertEquals("172.17.15.207", map.get("[2].host"));
        Assert.assertEquals(9300, map.get("[0].port"));

        jsonStr = "{\n" +
                "    \"shrinkNodeList\": [\n" +
                "        {\n" +
                "            \"host\": \"172.17.15.205\",\n" +
                "            \"port\": 9300\n" +
                "        },\n" +
                "        {\n" +
                "            \"host\": \"172.17.15.206\",\n" +
                "            \"port\": 9300\n" +
                "        },\n" +
                "        {\n" +
                "            \"host\": \"172.17.15.207\",\n" +
                "            \"port\": 9300\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        map = JsonUtil.jsonFlat(jsonStr);

        Assert.assertEquals(6, map.size());
        Assert.assertEquals("172.17.15.205", map.get("shrinkNodeList[0].host"));
        Assert.assertEquals("172.17.15.206", map.get("shrinkNodeList[1].host"));
        Assert.assertEquals("172.17.15.207", map.get("shrinkNodeList[2].host"));
        Assert.assertEquals(9300, map.get("shrinkNodeList[0].port"));
    }

    @Test
    public void multiJsonPatTest() {
        String jsonStr = "{\n" +
                "  \"index\": \"test3\",\n" +
                "  \"shard\": 0,\n" +
                "  \"primary\": false,\n" +
                "  \"current_state\": \"unassigned\",\n" +
                "  \"unassigned_info\": {\n" +
                "    \"reason\": \"INDEX_CREATED\",\n" +
                "    \"at\": \"2019-04-19T10:21:33.674Z\",\n" +
                "    \"last_allocation_status\": \"no_attempt\"\n" +
                "  },\n" +
                "  \"can_allocate\": \"no\",\n" +
                "  \"allocate_explanation\": \"cannot allocate because allocation is not permitted to any of the nodes\",\n" +
                "  \"node_allocation_decisions\": [\n" +
                "    {\n" +
                "      \"node_id\": \"M30vPtDgQreFK0u-ki5sVA\",\n" +
                "      \"node_name\": \"M30vPtD\",\n" +
                "      \"transport_address\": \"10.0.55.156:9300\",\n" +
                "      \"node_attributes\": {\n" +
                "        \"zone_id\": \"cn-hangzhou-b\",\n" +
                "        \"ml.max_open_jobs\": \"10\",\n" +
                "        \"ml.enabled\": \"true\"\n" +
                "      },\n" +
                "      \"node_decision\": \"no\",\n" +
                "      \"weight_ranking\": 1,\n" +
                "      \"deciders\": [\n" +
                "        {\n" +
                "          \"decider\": \"awareness\",\n" +
                "          \"decision\": \"NO\",\n" +
                "          \"explanation\": \"there are too many copies of the shard allocated to nodes with attribute [zone_id], there are [6] total configured shard copies for this shard id and [4] total attribute values, expected the allocated shard count per attribute [3] to be less than or equal to the upper bound of the required number of shards per attribute [2]\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"node_id\": \"WNrOJWFjQT-RKySuUo1DBA\",\n" +
                "      \"node_name\": \"WNrOJWF\",\n" +
                "      \"transport_address\": \"10.1.234.60:9300\",\n" +
                "      \"node_attributes\": {\n" +
                "        \"zone_id\": \"cn-hangzhou-h\",\n" +
                "        \"ml.max_open_jobs\": \"10\",\n" +
                "        \"ml.enabled\": \"true\"\n" +
                "      },\n" +
                "      \"node_decision\": \"no\",\n" +
                "      \"weight_ranking\": 2,\n" +
                "      \"deciders\": [\n" +
                "        {\n" +
                "          \"decider\": \"awareness\",\n" +
                "          \"decision\": \"NO\",\n" +
                "          \"explanation\": \"there are too many copies of the shard allocated to nodes with attribute [zone_id], there are [6] total configured shard copies for this shard id and [4] total attribute values, expected the allocated shard count per attribute [3] to be less than or equal to the upper bound of the required number of shards per attribute [2]\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"node_id\": \"DKR58lT2TjaODWc3L_bl1g\",\n" +
                "      \"node_name\": \"DKR58lT\",\n" +
                "      \"transport_address\": \"10.1.234.59:9300\",\n" +
                "      \"node_attributes\": {\n" +
                "        \"zone_id\": \"cn-hangzhou-h\",\n" +
                "        \"ml.max_open_jobs\": \"10\",\n" +
                "        \"ml.enabled\": \"true\"\n" +
                "      },\n" +
                "      \"node_decision\": \"no\",\n" +
                "      \"weight_ranking\": 3,\n" +
                "      \"deciders\": [\n" +
                "        {\n" +
                "          \"decider\": \"same_shard\",\n" +
                "          \"decision\": \"NO\",\n" +
                "          \"explanation\": \"the shard cannot be allocated to the same node on which a copy of the shard already exists [[test3][0], node[DKR58lT2TjaODWc3L_bl1g], [P], s[STARTED], a[id=4airW-EfTyy46KkNs0I9pg]]\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"decider\": \"awareness\",\n" +
                "          \"decision\": \"NO\",\n" +
                "          \"explanation\": \"there are too many copies of the shard allocated to nodes with attribute [zone_id], there are [6] total configured shard copies for this shard id and [4] total attribute values, expected the allocated shard count per attribute [3] to be less than or equal to the upper bound of the required number of shards per attribute [2]\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"node_id\": \"ibCPXibdS9KLz5lNQtenAQ\",\n" +
                "      \"node_name\": \"ibCPXib\",\n" +
                "      \"transport_address\": \"10.0.55.158:9300\",\n" +
                "      \"node_attributes\": {\n" +
                "        \"zone_id\": \"cn-hangzhou-b\",\n" +
                "        \"ml.max_open_jobs\": \"10\",\n" +
                "        \"ml.enabled\": \"true\"\n" +
                "      },\n" +
                "      \"node_decision\": \"no\",\n" +
                "      \"weight_ranking\": 4,\n" +
                "      \"deciders\": [\n" +
                "        {\n" +
                "          \"decider\": \"same_shard\",\n" +
                "          \"decision\": \"NO\",\n" +
                "          \"explanation\": \"the shard cannot be allocated to the same node on which a copy of the shard already exists [[test3][0], node[ibCPXibdS9KLz5lNQtenAQ], [R], s[STARTED], a[id=wSZwP27eTgKgcIehQV8_5Q]]\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"decider\": \"awareness\",\n" +
                "          \"decision\": \"NO\",\n" +
                "          \"explanation\": \"there are too many copies of the shard allocated to nodes with attribute [zone_id], there are [6] total configured shard copies for this shard id and [4] total attribute values, expected the allocated shard count per attribute [3] to be less than or equal to the upper bound of the required number of shards per attribute [2]\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"node_id\": \"csqi7htAQr-cYWfeiWrYcA\",\n" +
                "      \"node_name\": \"csqi7ht\",\n" +
                "      \"transport_address\": \"10.1.234.58:9300\",\n" +
                "      \"node_attributes\": {\n" +
                "        \"zone_id\": \"cn-hangzhou-h\",\n" +
                "        \"ml.max_open_jobs\": \"10\",\n" +
                "        \"ml.enabled\": \"true\"\n" +
                "      },\n" +
                "      \"node_decision\": \"no\",\n" +
                "      \"weight_ranking\": 5,\n" +
                "      \"deciders\": [\n" +
                "        {\n" +
                "          \"decider\": \"same_shard\",\n" +
                "          \"decision\": \"NO\",\n" +
                "          \"explanation\": \"the shard cannot be allocated to the same node on which a copy of the shard already exists [[test3][0], node[csqi7htAQr-cYWfeiWrYcA], [R], s[STARTED], a[id=2-hJCEiBTOet9HMuL6qUDg]]\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"decider\": \"awareness\",\n" +
                "          \"decision\": \"NO\",\n" +
                "          \"explanation\": \"there are too many copies of the shard allocated to nodes with attribute [zone_id], there are [6] total configured shard copies for this shard id and [4] total attribute values, expected the allocated shard count per attribute [3] to be less than or equal to the upper bound of the required number of shards per attribute [2]\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"node_id\": \"Bd3dSJgCR1KWPHNoSk_5bQ\",\n" +
                "      \"node_name\": \"Bd3dSJg\",\n" +
                "      \"transport_address\": \"10.0.55.157:9300\",\n" +
                "      \"node_attributes\": {\n" +
                "        \"zone_id\": \"cn-hangzhou-b\",\n" +
                "        \"ml.max_open_jobs\": \"10\",\n" +
                "        \"ml.enabled\": \"true\"\n" +
                "      },\n" +
                "      \"node_decision\": \"no\",\n" +
                "      \"weight_ranking\": 6,\n" +
                "      \"deciders\": [\n" +
                "        {\n" +
                "          \"decider\": \"same_shard\",\n" +
                "          \"decision\": \"NO\",\n" +
                "          \"explanation\": \"the shard cannot be allocated to the same node on which a copy of the shard already exists [[test3][0], node[Bd3dSJgCR1KWPHNoSk_5bQ], [R], s[STARTED], a[id=UVVcMi00Tr6E_GdnR3TYcg]]\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"decider\": \"awareness\",\n" +
                "          \"decision\": \"NO\",\n" +
                "          \"explanation\": \"there are too many copies of the shard allocated to nodes with attribute [zone_id], there are [6] total configured shard copies for this shard id and [4] total attribute values, expected the allocated shard count per attribute [3] to be less than or equal to the upper bound of the required number of shards per attribute [2]\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        Map<String, Object> map = JsonUtil.jsonFlat(jsonStr);

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        Assert.assertEquals(87, map.size());
        Assert.assertEquals(0, map.get("shard"));
        Assert.assertEquals("WNrOJWF", map.get("node_allocation_decisions[1].node_name"));
        Assert.assertEquals("same_shard", map.get("node_allocation_decisions[4].deciders[0].decider"));
    }

    @Test
    public void emptyJsonEqualTest() {
        Assert.assertTrue(JsonUtil.jsonEqual(null, null, true));
        Assert.assertTrue(JsonUtil.jsonEqual(null, null, false));
        Assert.assertTrue(JsonUtil.jsonEqual("", "", true));
        Assert.assertTrue(JsonUtil.jsonEqual("", "", false));
        Assert.assertTrue(JsonUtil.jsonEqual(null, "", true));
        Assert.assertTrue(JsonUtil.jsonEqual(null, "", false));
    }

    @Test
    public void mapJsonEqualTest() {
        String jsonStr1 = "{\n" +
                "  \"index\" : \"test3\",\n" +
                "  \"shard\" : \"0\",\n" +
                "  \"st\" : 0,\n" +
                "  \"primary\" : \"false\",\n" +
                "  \"replica\" : true\n" +
                "}";
        String jsonStr2 = "{\n" +
                "  \"index\" : \"test3\",\n" +
                "  \"st\" : 0,\n" +
                "  \"shard\" : \"0\",\n" +
                "  \"primary\" : \"false\",\n" +
                "  \"replica\" : true\n" +
                "}";
        String jsonStr3 = "{\n" +
                "  \"index\" : \"test3\",\n" +
                "  \"st\" : 0,\n" +
                "  \"primary\" : \"false\",\n" +
                "  \"replica\" : true\n" +
                "}";
        String jsonStr4 = "{\n" +
                "  \"index\" : \"test3\",\n" +
                "  \"st\" : 0,\n" +
                "  \"shard\" : \"1\",\n" +
                "  \"primary\" : \"false\",\n" +
                "  \"replica\" : true\n" +
                "}";
        String jsonStr5 = "{\n" +
                "  \"index\" : \"test3\",\n" +
                "  \"st\" : 0,\n" +
                "  \"shard\" : 0,\n" +
                "  \"primary\" : \"false\",\n" +
                "  \"replica\" : true\n" +
                "}";

        Assert.assertFalse(JsonUtil.jsonEqual(jsonStr1, "", true));
        Assert.assertTrue(JsonUtil.jsonEqual(jsonStr1, jsonStr1, true));
        Assert.assertTrue(JsonUtil.jsonEqual(jsonStr1, jsonStr1, false));
        Assert.assertTrue(JsonUtil.jsonEqual(jsonStr1, jsonStr2, true));
        Assert.assertTrue(JsonUtil.jsonEqual(jsonStr1, jsonStr2, false));

        Assert.assertFalse(JsonUtil.jsonEqual(jsonStr1, jsonStr3, false));
        Assert.assertFalse(JsonUtil.jsonEqual(jsonStr1, jsonStr4, false));
        Assert.assertFalse(JsonUtil.jsonEqual(jsonStr1, jsonStr5, false));

    }

    @Test
    public void listJsonEqualTest() {
        String jsonStr1 = "[\n" +
                "    {\n" +
                "        \"host\": \"172.17.15.205\",\n" +
                "        \"port\": 9300\n" +
                "    },\n" +
                "    {\n" +
                "        \"host\": \"172.17.15.206\",\n" +
                "        \"port\": 9300\n" +
                "    },\n" +
                "    {\n" +
                "        \"host\": \"172.17.15.207\",\n" +
                "        \"port\": 9300\n" +
                "    }\n" +
                "]";
        String jsonStr2 = "[\n" +
                "    {\n" +
                "        \"host\": \"172.17.15.205\",\n" +
                "        \"port\": 9300\n" +
                "    },\n" +
                "    {\n" +
                "        \"host\": \"172.17.15.207\",\n" +
                "        \"port\": 9300\n" +
                "    },\n" +
                "    {\n" +
                "        \"host\": \"172.17.15.206\",\n" +
                "        \"port\": 9300\n" +
                "    }\n" +
                "]";
        String jsonStr3 = "[\n" +
                "    {\n" +
                "        \"host\": \"172.17.15.205\",\n" +
                "        \"port\": 9300\n" +
                "    },\n" +
                "    {\n" +
                "        \"host\": \"172.17.15.206\",\n" +
                "        \"port\": 9300\n" +
                "    }\n" +
                "]";
        String jsonStr4 = "[\n" +
                "    {\n" +
                "        \"host\": \"172.17.15.205\",\n" +
                "        \"port\": 9300\n" +
                "    },\n" +
                "    {\n" +
                "        \"host\": \"172.17.15.207\",\n" +
                "        \"port\": 9300\n" +
                "    },\n" +
                "    {\n" +
                "        \"host\": \"172.17.15.206\",\n" +
                "        \"port\": 9301\n" +
                "    }\n" +
                "]";
        Assert.assertTrue(JsonUtil.jsonEqual(jsonStr1, jsonStr1, false));
        Assert.assertTrue(JsonUtil.jsonEqual(jsonStr1, jsonStr1, true));
        Assert.assertTrue(JsonUtil.jsonEqual(jsonStr1, jsonStr2, false));
        Assert.assertFalse(JsonUtil.jsonEqual(jsonStr1, jsonStr2, true));
        Assert.assertFalse(JsonUtil.jsonEqual(jsonStr1, jsonStr3, false));
        Assert.assertFalse(JsonUtil.jsonEqual(jsonStr1, jsonStr4, false));
    }
}