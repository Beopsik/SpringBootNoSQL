package com.example.springbootnosql;

import com.example.springbootnosql.account.Account;
import com.example.springbootnosql.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RedisRunner implements ApplicationRunner {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        values.set("beobsik", "whale");
        values.set("springboot", "2.0");
        values.set("hello", "world");

        Account account=new Account();
        account.setEmail("mbsik6082@naver.com");
        account.setUsername("beobsik");

        accountRepository.save(account);

        Optional<Account> byId=accountRepository.findById(account.getId());
        System.out.println(byId.get().getEmail());
        System.out.println(byId.get().getUsername());
    }
}
