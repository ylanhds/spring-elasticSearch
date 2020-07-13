package com.fablen.elasticsearch;

import com.alibaba.fastjson.JSON;
import com.fablen.elasticsearch.entity.Book;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchApplicationTests {


    @Autowired
    private RestHighLevelClient restHighLevelClient;

    //创建索引
    @Test
    public void testCreateIndex() throws IOException {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("book_wenxue");
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        System.out.println(createIndexResponse);
    }

    /**
     * 测试索引是否存在
     *
     * @throws IOException
     */
    @Test
    public void testExistIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest("book_wenxue");
        boolean exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    /**
     * 删除索引
     */
    @Test
    public void deleteIndex() throws IOException {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("book_wenxue");
        AcknowledgedResponse delete = restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        System.out.println(delete.isAcknowledged());
    }

    /**
     * 测试添加文档
     *
     * @throws IOException
     */
    @Test
    public void createDocument() throws IOException {
        Book book = new Book("三国演义", 188);
        IndexRequest request = new IndexRequest("book_wenxue");
        request.id("1");
        request.timeout(TimeValue.timeValueSeconds(1));
        request.timeout("1s");
        //将我们的数据放入请求，json
        request.source(JSON.toJSONString(book), XContentType.JSON);
        //客服端发送请求
        IndexResponse index = restHighLevelClient.index(request, RequestOptions.DEFAULT);
        System.out.println(index.toString());
        //对应我们的命令返回状态
        System.out.println(index.status());
    }

    //判断是否存在文档
    @Test
    public void testIsExist() throws IOException {
        GetRequest getRequest = new GetRequest("book_wenxue", "1");
        //不获取返回的source的上下文
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        getRequest.storedFields("_none_");
        boolean exists = restHighLevelClient.exists(getRequest, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    //获取文档信息
    @Test
    public void testGetDocument() throws IOException {
        GetRequest getRequest = new GetRequest("book_wenxue", "1");
        GetResponse response = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        //打印文档信息
        System.out.println(response.getSourceAsString());
        System.out.println(response);
    }

    //更新文档信息
    @Test
    public void testUpdateDocument() throws IOException {
        UpdateRequest request = new UpdateRequest("book_wenxue", "1");
        request.timeout("1s");
        Book book = new Book("三国", 333);
        request.doc(JSON.toJSONString(book), XContentType.JSON);
        UpdateResponse update = restHighLevelClient.update(request, RequestOptions.DEFAULT);
        System.out.println(update);
        System.out.println(update.status());
    }

    //删除文档
    @Test
    public void testDeleteDocument() throws IOException {
        DeleteRequest request = new DeleteRequest("book_wenxue", "1");
        request.timeout("10s");
        DeleteResponse update = restHighLevelClient.delete(request, RequestOptions.DEFAULT);
        System.out.println(update.status());
    }

    //批量插入数据
    @Test
    public void testBulkRequest() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("10s");
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("三国演义", 1));
        books.add(new Book("红楼梦", 12));
        books.add(new Book("西游记", 13));
        books.add(new Book("水浒传", 14));
        books.add(new Book("哥德巴赫猜想", 15));
        for (int i = 0; i < books.size(); i++) {
            bulkRequest.add(
                    new IndexRequest("book_wenxue")
                            .id("" + i + 1)
                            .source(JSON.toJSONString(books.get(i)), XContentType.JSON)
            );
        }
        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulk);

    }
}