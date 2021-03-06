package fr.gtm.project.proxibanque.business;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import fr.gtm.project.proxibanque.dao.ClientDao;
import fr.gtm.project.proxibanque.entity.Client;

public class SearchClientComponent {
	private static Integer COUNTER = 0;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SearchClientComponent.class);

	@Autowired
	private ClientDao clientDao;

	private final Integer id;
	private String search;

	private Set<Client> resultCache;

	public SearchClientComponent() {
		this.id = SearchClientComponent.COUNTER++;
		this.resultCache = new HashSet<>();
	}

	public boolean searchValid() {
		String[] keywords = this.search.split(" ");
		if (keywords.length == 2) {
			String keyword1 = keywords[0];
			String keyword2 = keywords[1];
			this.resultCache.addAll(this.clientDao
					.findAllByFirstnameAndLastname(keyword1, keyword2));
			this.resultCache.addAll(this.clientDao
					.findAllByFirstnameAndLastname(keyword2, keyword1));
		}
		else {
			this.resultCache.addAll(this.clientDao.findAllByFirstnameOrLastname(keywords[0], keywords[0] ));
			LOGGER.info("{}", this.resultCache);
		}
		return this.resultCache.size() > 0;
	}

	public Client getValidatedClient(LocalDate birthDate) {
		Client result = null;
		for (Client client : this.resultCache) {
			if (client.getBirthDate().equals(birthDate)) {
				result = client;
				break;
			}
		}
		return result;
	}

	public Integer getId() {
		return id;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
}
