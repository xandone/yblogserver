import com.xandone.yblog.mapper.ArticleMapper;
import com.xandone.yblog.mapper.BannerMapper;
import com.xandone.yblog.pojo.ArticleBean;
import com.xandone.yblog.pojo.BannerBean;
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
            bannerBean.setImgUrl("https://upload-images.jianshu.io/upload_images/2518499-3d5a6ec6bc7f7efd.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240");
            bannerBean.setTitle("长河落日圆");
            bannerBean.setPageViews(0);
            bannerBean.setUpTime(new Date());
            mapper.addBanner(bannerBean);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}