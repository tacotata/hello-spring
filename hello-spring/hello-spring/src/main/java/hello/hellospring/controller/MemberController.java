package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    //스프링컨테이너가 스프링이 관리함 객체를 생성해서 스프링이 들고있음
    //컨트롤러에서 외부 요청을 받고
    /*스프링 빈 등록하는 방법 2가지
    1. 컴포넌트 스캔과 자동 의존관계 설정 -@Controller-> 이런거에 @component 포함되어 있기때문에 빈에 등록됨
    2. 자바 코드로 직접 스프링 빈 등록
    @component 있으면 스프링 빈으로 자동 등록됨
    @Autowired  연결 해줌
    하위 패키지를 자동으로 등록함  패키지를 포함한 하위만 쫙 따라들어가요
     */
    //빈을 등록할 때 기본으로 싱글톤으로 등록한다(하나만 등록해서 공유)
    //따라서 같은 스피링 빈이면 모두 같은 인스턴스  설정으로 다르게 할 수 있지만 특수 케이스임
    private MemberService memberService;

    //멤버서비스를 연걸해줌 의존관계 주입 DI
    //DI 3가지 방법 생성자, 주입 , 필드 주입 , setter 주입
    //필드주입 별로 안좋음 바꿀 수 있는 방법이 아예 없다
    //@Autowired private MemberService memberService;
    //setter 주입 public하게 노출됨 호출 안할 메서드가 호출되면 안됨 조립시점에 조립하고 변경 못하게 막아버려야지
    //    @Autowired
    //    public void setMemberService(MemberService memberService) {
    //        this.memberService = memberService;
    //    }

    //생성자 주입
   @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
       System.out.println("memberService =" +memberService.getClass());
    }

    @GetMapping("/members/new")
    public String createForm(){
       return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String Create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member.getName() = " + member.getName());
        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> member = memberService.findMembers();
        model.addAttribute("members",member);
       return "members/memberList";
    }
}
