package com.web.blog;

import com.web.blog.dao.join.PortfolioTagDao;
import com.web.blog.dao.portfolio.PortfolioDao;
import com.web.blog.dao.tag.TagDao;

import com.web.blog.property.FileUploadProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
@EnableConfigurationProperties({ FileUploadProperties.class })
public class WebBlogApplication implements CommandLineRunner {

	@Autowired
	private PortfolioDao pr;

	@Autowired
	private TagDao tr;

	@Autowired
	private PortfolioTagDao ptr;

	public static void main(final String[] args) {

		SpringApplication.run(WebBlogApplication.class, args);
	}

	@Bean
	public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
		return new HiddenHttpMethodFilter();
		// git ignore3
	}

	@Override
	@Transactional
	public void run(final String... args) throws Exception {

		/*
		 * final PortfolioTag join_t = new PortfolioTag(); final Optional<Portfolio> t_p
		 * = pr.findById(2); final Optional<Tag> t_t = tr.findById(2);
		 * 
		 * if (t_p.isPresent() && t_t.isPresent()) {
		 * 
		 * join_t.setPortfolio(t_p.get()); join_t.setTag(t_t.get());
		 * 
		 * if (!ptr.getPortfolioTagByPortfolioAndTag(t_p.get(), t_t.get()).isPresent()){
		 * ptr.save(join_t); }
		 * 
		 * }
		 * 
		 * final List<PortfolioTag> portfolioTags = t_p.get().getPortfolioTags();
		 * 
		 * List<Portfolio> list = pr.findPortfolioByUid("12312");
		 * System.out.println(list); // List<MemberPhone> memberProducts =
		 * t_p.get().getMemberProducts();
		 * 
		 * //태크에 따라서 프로젝트 리스트 반환 //유저 id로 프로젝트 id를 전부가져옴 //프로젝트 id가 가지는 태그들 가져옴. //태그
		 * 리스트를 가지고 있고 //tid를 1,2,3을 가지고
		 * 
		 * //api 1) //pr.findByUid(uid); 프로젝트 리스트를 가져오고 //중복안되게 tagid를 긁어와서 리턴
		 * 
		 * //api 2) //tagid 리스트를 입력으로 받고 //tagid 리스트와 연관된 프로젝트들을 where uid = ' '랑 중복안되게
		 * 출력
		 * 
		 * 
		 * 
		 * for (final PortfolioTag pt : portfolioTags) {
		 * System.out.println(pt.getTag().getTagName()); }
		 * 
		 */
	}

}
