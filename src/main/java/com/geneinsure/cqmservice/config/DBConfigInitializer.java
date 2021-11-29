package com.geneinsure.cqmservice.config;

import com.geneinsure.cqmservice.exception.ResourceNotFoundException;
import com.geneinsure.cqmservice.model.entity.*;
import com.geneinsure.cqmservice.model.enums.AnswerType;
import com.geneinsure.cqmservice.model.enums.RoleType;
import com.geneinsure.cqmservice.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Chirinda Nyasha 27/11/2021
 */

@Component
public class DBConfigInitializer {

    private static final Logger log = LoggerFactory.getLogger(DBConfigInitializer.class);

    private final RoleServiceImpl roleService;
    private final QuestionServiceImpl questionService;
    private final QuestionCategoryServiceImpl questionCategoryService;
    private final ReviewTargetServiceImpl reviewTargetService;
    private final SupervisorServiceImpl supervisorService;
    private final AgentServiceImpl agentService;
    private final CustomerServiceImpl customerService;
    private final UserServiceImpl userService;

    @Autowired
    public DBConfigInitializer(RoleServiceImpl roleService, QuestionServiceImpl questionService,
                               QuestionCategoryServiceImpl questionCategoryService, ReviewTargetServiceImpl reviewTargetService,
                               SupervisorServiceImpl supervisorService, AgentServiceImpl agentService, CustomerServiceImpl customerService,
                               UserServiceImpl userService) {
        this.roleService = roleService;
        this.questionService = questionService;
        this.questionCategoryService = questionCategoryService;
        this.reviewTargetService = reviewTargetService;
        this.supervisorService = supervisorService;
        this.agentService = agentService;
        this.customerService = customerService;
        this.userService = userService;
    }

    // Listen to when the application has fully started and is ready to process requests
    // Check if the required data for the service to run is in db
    // If not load the default data e.g. Questions and their Categories

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        log.info("Checking for initial service data in db.");

        // 1. User roles
        if (roleService.findAll().isEmpty()) {
            log.info("No roles in db, creating defaults.");
            // No roles in db, insert default roles

            List<Role> defaultRoles = createDefaultRoles();

            roleService.saveAll(defaultRoles);

            log.info("Completed creating application default Roles.");
        }

        // 2. QuestionCategories
        if (questionCategoryService.findAll().isEmpty()) {
            log.info("No QuestionCategories in db, loading defaults.");

            List<QuestionCategory> defaultQCs = createDefaultQuestionCategories();

            questionCategoryService.saveAll(defaultQCs);

            log.info("Completed creating application default QuestionCategories.");
        }

        // 3. Questions
        if (questionService.findAll().isEmpty()) {
            log.info("No Questions in db, loading defaults.");

            List<Question> defaultQuestions = createDefaultQuestions();

            questionService.saveAll(defaultQuestions);

            log.info("Completed creating application default Questions.");
        }

        // 4. ReviewTargets
        if (reviewTargetService.findAll().isEmpty()) {
            log.info("No ReviewTargets in db, loading defaults.");

            List<ReviewTarget> defaultReviewTargets = createDefaultReviewTargets();

            reviewTargetService.saveAll(defaultReviewTargets);

            log.info("Completed creating application default ReviewTargets.");
        }

        // 5. Supervisor
        if (userService.findUserByEmailAddress("supervisor@gene.co.zw").isEmpty()) {
            log.info("No default Supervisor in db, loading default.");

            User defaultSupervisorUser = new User();
            defaultSupervisorUser.setUsername("supervisor");
            defaultSupervisorUser.setFirstName("Tanaka");
            defaultSupervisorUser.setLastName("Supervisor");
            defaultSupervisorUser.setPassword("password");
            defaultSupervisorUser.setActive(true);

            ContactDetail cd = new ContactDetail();
            cd.setEmailAddress("supervisor@gene.co.zw");
            cd.setMobileNumber(263710987321L);
            defaultSupervisorUser.setContactDetail(cd);

            Role userRole = roleService.findRoleByName(RoleType.ROLE_SUPERVISOR)
                    .orElseThrow(() -> new ResourceNotFoundException("Role", "name", RoleType.ROLE_SUPERVISOR.toString()));
            defaultSupervisorUser.setRoles(Collections.singleton(userRole));

            // User savedDefaultUser = userService.save(defaultSupervisorUser);

            Supervisor defaultSupervisor = new Supervisor();
            defaultSupervisor.setNumber(101L);
            defaultSupervisor.setUser(defaultSupervisorUser);

            supervisorService.save(defaultSupervisor);

            log.info("Completed creating application default Supervisor.");
        }

        // 6. Agent
        if (userService.findUserByEmailAddress("agent@gene.co.zw").isEmpty()) {
            log.info("No default Agent in db, loading default.");

            User defaultUser = new User();
            defaultUser.setUsername("agent");
            defaultUser.setFirstName("Tapfuma");
            defaultUser.setLastName("Agent");
            defaultUser.setPassword("password");
            defaultUser.setActive(true);

            ContactDetail cd = new ContactDetail();
            cd.setEmailAddress("agent@gene.co.zw");
            cd.setMobileNumber(263712987321L);
            defaultUser.setContactDetail(cd);

            Role userRole = roleService.findRoleByName(RoleType.ROLE_AGENT)
                    .orElseThrow(() -> new ResourceNotFoundException("Role", "name", RoleType.ROLE_AGENT.toString()));
            defaultUser.setRoles(Collections.singleton(userRole));

            // User savedDefaultUser = userService.save(defaultUser);

            Agent defaultAgent = new Agent();
            defaultAgent.setNumber(201L);
            defaultAgent.setUser(defaultUser);

            defaultAgent.setSupervisor(supervisorService.findAll().get(0));

            agentService.save(defaultAgent);

            log.info("Completed creating application default Customer.");
        }

        // 7. User
        if (userService.findUserByEmailAddress("tarisai@acme.co.zw").isEmpty()) {
            log.info("No default Customer in db, loading default.");

            User defaultUser = new User();
            defaultUser.setUsername("tarisai");
            defaultUser.setFirstName("Tarisai");
            defaultUser.setLastName("Mutema");
            defaultUser.setPassword("password");
            defaultUser.setActive(true);

            ContactDetail cd = new ContactDetail();
            cd.setEmailAddress("tarisai@acme.co.zw");
            cd.setMobileNumber(263777987321L);
            defaultUser.setContactDetail(cd);

            Role userRole = roleService.findRoleByName(RoleType.ROLE_CUSTOMER)
                    .orElseThrow(() -> new ResourceNotFoundException("Role", "name", RoleType.ROLE_CUSTOMER.toString()));
            defaultUser.setRoles(Collections.singleton(userRole));

            // User savedDefaultUser = userService.save(defaultUser);

            Customer defaultCustomer = new Customer();
            defaultCustomer.setNumber(301L);
            defaultCustomer.setUser(defaultUser);

            customerService.save(defaultCustomer);

            log.info("Completed creating application default Customer.");
        }

        log.info("Completed checking application default data in db.");
    }

    private List<Role> createDefaultRoles() {
        List<Role> defaultRoles = new LinkedList<>();

        Role adminRole = new Role();
        adminRole.setName(RoleType.ROLE_ADMIN);
        adminRole.setDescription("System Administrator");
        defaultRoles.add(adminRole);

        Role supervisorRole = new Role();
        supervisorRole.setName(RoleType.ROLE_SUPERVISOR);
        supervisorRole.setDescription("Company Supervisor");
        defaultRoles.add(supervisorRole);

        Role agentRole = new Role();
        agentRole.setName(RoleType.ROLE_AGENT);
        agentRole.setDescription("Company Agent");
        defaultRoles.add(agentRole);

        Role customerRole = new Role();
        customerRole.setName(RoleType.ROLE_CUSTOMER);
        customerRole.setDescription("Company Customer");
        defaultRoles.add(customerRole);

        return defaultRoles;
    }

    private List<QuestionCategory> createDefaultQuestionCategories() {
        List<QuestionCategory> defaultQCs = new LinkedList<>();

        QuestionCategory qc1 = new QuestionCategory();
        qc1.setName("Greeting");
        defaultQCs.add(qc1);

        QuestionCategory qc2 = new QuestionCategory();
        qc2.setName("Handling Contact");
        defaultQCs.add(qc2);

        QuestionCategory qc3 = new QuestionCategory();
        qc3.setName("Solution Information");
        defaultQCs.add(qc3);

        QuestionCategory qc4 = new QuestionCategory();
        qc4.setName("Notifications");
        defaultQCs.add(qc4);

        QuestionCategory qc5 = new QuestionCategory();
        qc5.setName("Telephony Skills");
        defaultQCs.add(qc5);

        QuestionCategory qc6 = new QuestionCategory();
        qc6.setName("Soft Skills");
        defaultQCs.add(qc6);

        QuestionCategory qc7 = new QuestionCategory();
        qc7.setName("End Call");
        defaultQCs.add(qc7);

        return defaultQCs;
    }

    private List<Question> createDefaultQuestions() {
        List<Question> defaultQuestions = new LinkedList<>();

        String greetingCatName = "Greeting";
        QuestionCategory greetingCat = questionCategoryService.findQuestionCategoryByName(greetingCatName)
                .orElseThrow(() -> new ResourceNotFoundException("QuestionCategory", "name", greetingCatName));

        Question q1 = new Question();
        q1.setNumber(1L);
        q1.setText("Did the agent professionally greet the client");
        q1.setQuestionCategory(greetingCat);
        q1.setAnswerType(AnswerType.YES_NO);
        defaultQuestions.add(q1);

        Question q2 = new Question();
        q2.setNumber(2L);
        q2.setText("Did the agent introduce him/ herself");
        q2.setQuestionCategory(greetingCat);
        q2.setAnswerType(AnswerType.YES_NO);
        defaultQuestions.add(q2);

        Question q3 = new Question();
        q3.setNumber(3L);
        q3.setText("Did the agent mention the company's name");
        q3.setQuestionCategory(greetingCat);
        q3.setAnswerType(AnswerType.YES_NO);
        defaultQuestions.add(q3);

        Question q4 = new Question();
        q4.setNumber(4L);
        q4.setText("Did the agent fully describe the company's product offering");
        q4.setQuestionCategory(greetingCat);
        q4.setAnswerType(AnswerType.YES_NO);
        defaultQuestions.add(q4);

        Question q5 = new Question();
        q5.setNumber(5L);
        q5.setText("Did the agent confirm or ask for the potential client's name");
        q5.setQuestionCategory(greetingCat);
        q5.setAnswerType(AnswerType.YES_NO);
        defaultQuestions.add(q5);

        String handlingContactCatName = "Handling Contact";
        QuestionCategory handlingContactCat = questionCategoryService.findQuestionCategoryByName(handlingContactCatName)
                .orElseThrow(() -> new ResourceNotFoundException("QuestionCategory", "name", handlingContactCatName));

        Question q6 = new Question();
        q6.setNumber(6L);
        q6.setText("Did the agent display knowledge of previous calls");
        q6.setQuestionCategory(handlingContactCat);
        q6.setAnswerType(AnswerType.YES_NO);
        defaultQuestions.add(q6);

        Question q7 = new Question();
        q7.setNumber(7L);
        q7.setText("Did the agent avoid the use of slang");
        q7.setQuestionCategory(handlingContactCat);
        q7.setAnswerType(AnswerType.YES_NO);
        defaultQuestions.add(q7);

        Question q8 = new Question();
        q8.setNumber(8L);
        q8.setText("Did the agent mention the client's name at least 3 times");
        q8.setQuestionCategory(handlingContactCat);
        q8.setAnswerType(AnswerType.YES_NO);
        defaultQuestions.add(q8);

        Question q9 = new Question();
        q9.setNumber(9L);
        q9.setText("Did the agent thank the client for his/her time");
        q9.setQuestionCategory(handlingContactCat);
        q9.setAnswerType(AnswerType.YES_NO);
        defaultQuestions.add(q9);

        Question q10 = new Question();
        q10.setNumber(10L);
        q10.setText("Did the agent correctly dispose the call");
        q10.setQuestionCategory(handlingContactCat);
        q10.setAnswerType(AnswerType.YES_NO);
        defaultQuestions.add(q10);

        String solutionInfoCatName = "Solution Information";
        QuestionCategory solutionInfoCat = questionCategoryService.findQuestionCategoryByName(solutionInfoCatName)
                .orElseThrow(() -> new ResourceNotFoundException("QuestionCategory", "name", solutionInfoCatName));

        Question q11 = new Question();
        q11.setNumber(11L);
        q11.setText("Full details of the client were obtained");
        q11.setQuestionCategory(solutionInfoCat);
        q11.setAnswerType(AnswerType.RANGE);
        defaultQuestions.add(q11);

        Question q12 = new Question();
        q12.setNumber(12L);
        q12.setText("Confirmation of details provided by client");
        q12.setQuestionCategory(solutionInfoCat);
        q12.setAnswerType(AnswerType.RANGE);
        defaultQuestions.add(q12);

        String notificationsCatName = "Notifications";
        QuestionCategory notificationsCat = questionCategoryService.findQuestionCategoryByName(notificationsCatName)
                .orElseThrow(() -> new ResourceNotFoundException("QuestionCategory", "name", notificationsCatName));

        Question q13 = new Question();
        q13.setNumber(13L);
        q13.setText("Agent disclosed the appropriate turn around times");
        q13.setQuestionCategory(notificationsCat);
        q13.setAnswerType(AnswerType.RANGE);
        defaultQuestions.add(q13);

        Question q14 = new Question();
        q14.setNumber(14L);
        q14.setText("Correct procedures followed for placing a customer on hold");
        q14.setQuestionCategory(notificationsCat);
        q14.setAnswerType(AnswerType.RANGE);
        defaultQuestions.add(q14);

        String telephonySkillsCatName = "Telephony Skills";
        QuestionCategory telephonySkillsCat = questionCategoryService.findQuestionCategoryByName(telephonySkillsCatName)
                .orElseThrow(() -> new ResourceNotFoundException("QuestionCategory", "name", telephonySkillsCatName));

        Question q15 = new Question();
        q15.setNumber(15L);
        q15.setText("Customer was notified of all the relevant documents and information");
        q15.setQuestionCategory(telephonySkillsCat);
        q15.setAnswerType(AnswerType.RANGE);
        defaultQuestions.add(q15);

        Question q16 = new Question();
        q16.setNumber(16L);
        q16.setText("Agent did not interrupt or talk over the customer");
        q16.setQuestionCategory(telephonySkillsCat);
        q16.setAnswerType(AnswerType.RANGE);
        defaultQuestions.add(q16);

        String softSkillsCatName = "Soft Skills";
        QuestionCategory softSkillsCat = questionCategoryService.findQuestionCategoryByName(softSkillsCatName)
                .orElseThrow(() -> new ResourceNotFoundException("QuestionCategory", "name", softSkillsCatName));

        Question q17 = new Question();
        q17.setNumber(17L);
        q17.setText("Agent used effective questioning skills");
        q17.setQuestionCategory(softSkillsCat);
        q17.setAnswerType(AnswerType.RANGE);
        defaultQuestions.add(q17);

        Question q18 = new Question();
        q18.setNumber(18L);
        q18.setText("Agent demonstrated active listening");
        q18.setQuestionCategory(softSkillsCat);
        q18.setAnswerType(AnswerType.RANGE);
        defaultQuestions.add(q18);

        String endCallCatName = "End Call";
        QuestionCategory endCallCat = questionCategoryService.findQuestionCategoryByName(endCallCatName)
                .orElseThrow(() -> new ResourceNotFoundException("QuestionCategory", "name", endCallCatName));

        Question q19 = new Question();
        q19.setNumber(19L);
        q19.setText("Agent dealt with all the issues raised");
        q19.setQuestionCategory(endCallCat);
        q19.setAnswerType(AnswerType.RANGE);
        defaultQuestions.add(q19);

        Question q20 = new Question();
        q20.setNumber(20L);
        q20.setText("Did the agent proactively ask for a sale");
        q20.setQuestionCategory(endCallCat);
        q20.setAnswerType(AnswerType.RANGE);
        defaultQuestions.add(q20);

        return defaultQuestions;
    }

    private List<ReviewTarget> createDefaultReviewTargets() {
        List<ReviewTarget> defaultReviewTargets = new LinkedList<>();

        ReviewTarget rt0 = new ReviewTarget();
        rt0.setMinPercentage(0);
        rt0.setMaxPercentage(0);
        rt0.setMessage("Start Selections");
        defaultReviewTargets.add(rt0);

        ReviewTarget rt1 = new ReviewTarget();
        rt1.setMinPercentage(0);
        rt1.setMaxPercentage(50);
        rt1.setMessage("Unacceptable - work needed");
        defaultReviewTargets.add(rt1);

        ReviewTarget rt2 = new ReviewTarget();
        rt2.setMinPercentage(50);
        rt2.setMaxPercentage(64);
        rt2.setMessage("Acceptable - Room for Improvement");
        defaultReviewTargets.add(rt2);

        ReviewTarget rt3 = new ReviewTarget();
        rt3.setMinPercentage(64);
        rt3.setMaxPercentage(82);
        rt3.setMessage("A good result - keep on improving");
        defaultReviewTargets.add(rt3);

        ReviewTarget rt4 = new ReviewTarget();
        rt4.setMinPercentage(82);
        rt4.setMaxPercentage(100);
        rt4.setMessage("Wow, Excellent");
        defaultReviewTargets.add(rt4);

        return defaultReviewTargets;
    }

}
