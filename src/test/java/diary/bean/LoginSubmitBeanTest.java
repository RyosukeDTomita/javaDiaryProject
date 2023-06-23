package diary.bean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.apache.struts.mock.MockHttpSession;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import diary.annotation.Fast;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * LoginSubmitBean Test.
 * check() method test are skipped.
 * See {@link diary.dataaccess.LoginDAO}.
 * 
 * @author iceman
 * @version 1.0
 */
// public class LoginSubmitBeanTest {
// /**
// * Instantiating test class.
// */
// public class InstantiateTest {
// /**
// * test create {@code LoginSubmitBean} instance.
// */
// @Fast
// @Test
// public void defaultConstructorTest() {
// String className = "diary.bean.LoginSubmitBean";
// try {
// Class<?> clazz = Class.forName(className); // return "className" Class
// Object.
// // newInstance() is deprecaetd.
// ExecuteInterface usi = (ExecuteInterface)
// clazz.getDeclaredConstructor().newInstance();
// } catch (ClassNotFoundException e) {
// e.printStackTrace();
// } catch (Exception e) {
// e.printStackTrace();
// }
// }

// }

// static ExecuteInterface usi;

// /**
// * Before test, create {@code LoginSubmitBean} Instance.
// */
// @BeforeAll
// public static void setUpClass() {
// String className = "diary.bean.LoginSubmitBean";
// try {
// Class<?> clazz = Class.forName(className); // return "className" Class
// Object.
// // newInstance() is deprecate.
// usi = (ExecuteInterface) clazz.getDeclaredConstructor().newInstance();
// } catch (ClassNotFoundException e) {
// e.printStackTrace();
// } catch (Exception e) {
// e.printStackTrace();
// }
// }

// /**
// * This test expect {@code success} pattern.
// * {@code request}, {@code response} are stub.
// */
// @Fast
// @Test
// public void executeSuccessTest() {
// // create request, response stub.
// HttpServletRequest request = mock(HttpServletRequest.class);
// when(request.getParameter("loginID")).thenReturn("hoge");
// when(request.getParameter("password")).thenReturn("password");
// when(request.getSession(true)).thenReturn(new MockHttpSession());
// HttpServletResponse response = mock(HttpServletResponse.class);

// assertEquals("success", usi.execute(request, response));
// }

// /**
// * This test expect {@code failure} pattern.
// * {@code request}, {@code response} are stub.
// */
// @Fast
// @Test
// public void executeFailureTest() {
// // create request, response stub.
// HttpServletRequest request = mock(HttpServletRequest.class);
// when(request.getParameter("loginID")).thenReturn("anonymous");
// when(request.getParameter("password")).thenReturn("anonymous");
// HttpServletResponse response = mock(HttpServletResponse.class);

// assertEquals("failure", usi.execute(request, response));
// }

// }
