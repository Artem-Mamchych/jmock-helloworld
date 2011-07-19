import java.lang.reflect.Field;
import java.util.Arrays;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;

public class MockExampleTest {

    public static void main(String[] args) throws SecurityException,
            NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        final Mockery mockContext = new JUnit4Mockery() {
            {
                this.setImposteriser(ClassImposteriser.INSTANCE);
            }
        };

        final Member testMember = new Member() {

            @Override
            public int[] getValues() {
                return new int[] { 5, 6, 7, 8 };
            }
        };

        final Factory mockFactory = mockContext.mock(Factory.class);
        final MemberImpl mockMember = mockContext.mock(MemberImpl.class);

        final Field singleton = Factory.class.getDeclaredField("INSTANCE");
        singleton.setAccessible(true);
        singleton.set(null, mockFactory);

        final Field fld = Factory.class.getDeclaredField("member");
        fld.setAccessible(true);
        fld.set(mockFactory, mockMember);

        mockContext.checking(new Expectations() {
            {
                this.allowing(mockMember).getValues();
                this.will(returnValue(testMember.getValues()));
            }
            {
                this.allowing(mockMember);
                this.will(returnValue(null));
            }
        });

        final Member resultMember = Factory.getMember();
        System.out.println(Arrays.toString(resultMember.getValues()));
    }
}
