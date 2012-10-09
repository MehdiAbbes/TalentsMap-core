package com.mehdi.abbes.tm.domain;

import com.mehdi.abbes.tm.domain.Category;
import com.mehdi.abbes.tm.repository.CategoryRepository;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

@Configurable
@Component
public class CategoryDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<Category> data;

	@Autowired
    CategoryRepository categoryRepository;

	public Category getNewTransientCategory(int index) {
        Category obj = new Category();
        setDescription(obj, index);
        setLabel(obj, index);
        return obj;
    }

	public void setDescription(Category obj, int index) {
        String description = "description_" + index;
        if (description.length() > 250) {
            description = description.substring(0, 250);
        }
        obj.setDescription(description);
    }

	public void setLabel(Category obj, int index) {
        String label = "label_" + index;
        if (label.length() > 25) {
            label = new Random().nextInt(10) + label.substring(1, 25);
        }
        obj.setLabel(label);
    }

	public Category getSpecificCategory(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Category obj = data.get(index);
        Long id = obj.getId();
        return categoryRepository.findOne(id);
    }

	public Category getRandomCategory() {
        init();
        Category obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return categoryRepository.findOne(id);
    }

	public boolean modifyCategory(Category obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = categoryRepository.findAll(new org.springframework.data.domain.PageRequest(from / to, to)).getContent();
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Category' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Category>();
        for (int i = 0; i < 10; i++) {
            Category obj = getNewTransientCategory(i);
            try {
                categoryRepository.save(obj);
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            categoryRepository.flush();
            data.add(obj);
        }
    }
}
