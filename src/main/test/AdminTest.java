import com.xandone.yblog.mapper.*;
import com.xandone.yblog.pojo.*;
import com.xandone.yblog.utils.IDUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class AdminTest {
    @Test
    public void addArt() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        ArticleMapper mapper = context.getBean(ArticleMapper.class);

        ArticleBean articleBean = new ArticleBean();
        articleBean.setArtId("123");
        articleBean.setArtUserId("1");
        articleBean.setTitle("FIFA探讨世界杯扩军：中国可能要进世界杯？");
        articleBean.setContent("2018年4月，南美足协主席多明格斯公开发言，希望国际足联考虑在2022年实现世界杯扩军，因凡蒂诺顺水推舟，"
                + "开始寻求卡塔尔的合作。但卡塔尔不愿意与周边国家共同举办世界杯，而它自身的场馆数量，又无法满足48支球队比赛的需求，这一计划才最终作罢。");
        articleBean.setPostTime(new Date());
        mapper.addArticle(articleBean);

    }

    @Test
    public void addBanner() {
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
            BannerMapper mapper = context.getBean(BannerMapper.class);
            BannerBean bannerBean = new BannerBean();
            bannerBean.setUserId("250");
            bannerBean.setArticelId(IDUtils.RandomId());
            bannerBean.setImgUrl("http://q1kdflm5d.bkt.clouddn.com/Fli_OgeVZBvI8AYpWCh6hbjHXBkE");
            bannerBean.setTitle("长河落日圆");
            bannerBean.setPageViews(0);
            bannerBean.setUpTime(new Date());
            mapper.addBanner(bannerBean);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void addComment() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        CommentMapper mapper = context.getBean(CommentMapper.class);

        CommentBean commentBean = new CommentBean();
        commentBean.setCommentId(IDUtils.RandomId());
        commentBean.setArtId("157483426134816");
        commentBean.setCommentUserId("0");
        commentBean.setCommentDetails("写得好");
        commentBean.setCommentDate(new Date());

        mapper.addComment(commentBean);
    }

    @Test
    public void addEssay() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        EssayMapper mapper = context.getBean(EssayMapper.class);

        EssayBean articleBean = new EssayBean();
        articleBean.setEssayId("123");
        articleBean.setEssayUserId("1");
        articleBean.setTitle("FIFA探讨世界杯扩军：中国可能要进世界杯？");
        articleBean.setContent("2018年4月，南美足协主席多明格斯公开发言，希望国际足联考虑在2022年实现世界杯扩军，因凡蒂诺顺水推舟，"
                + "开始寻求卡塔尔的合作。但卡塔尔不愿意与周边国家共同举办世界杯，而它自身的场馆数量，又无法满足48支球队比赛的需求，这一计划才最终作罢。");
        articleBean.setPostTime(new Date());
        mapper.addEssay(articleBean);

    }


    @Test
    public void addAdmin() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        AdminMapper mapper = context.getBean(AdminMapper.class);

        AdminBean adminBean = new AdminBean(
                "admin",
                "123",
                "Admin",
                "250",
                new Date());
        adminBean.setAdminIcon("http://p1.pstatp.com/large/pgc-image/8f5a9eaea7cb426c895a67e6557eec32");
        adminBean.setTotalArts(0);
        adminBean.setEmail("765478955@qq.com");

        mapper.addAdmin(adminBean);
    }
}
