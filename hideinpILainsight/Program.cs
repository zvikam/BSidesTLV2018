using System;
using System.Diagnostics;
using System.Reflection;
using System.Reflection.Emit;
using System.Text;

namespace solve
{
	public class Sanchez
	{
		//
		// Static Methods
		//
		public static void Main(string[] args)
		{
			/*
			if (Debugger.IsAttached) {
				Console.WriteLine ("Sometimes science is a lot more art than science. A lot of people don't get that.");
				Console.ReadKey ();
				return;
			}
			if (new Random (Guid.NewGuid ().GetHashCode ()).Next (312) < 312) {
				return;
			}
			*/
			byte[] array = new byte[] {
				32,
				70,
				76,
				69,
				127,
				10,
				22,
				11,
				22,
				12,
				32,
				0,
				62,
				0,
				2,
				13,
				32,
				0,
				0,
				0,
				1,
				19,
				4,
				32,
				0,
				64,
				4,
				0,
				19,
				5,
				22,
				19,
				6,
				32,
				0,
				1,
				1,
				2,
				19,
				7,
				22,
				19,
				8,
				43,
				49,
				17,
				8,
				31,
				11,
				48,
				15,
				3,
				17,
				8,
				3,
				142,
				105,
				93,
				145,
				3,
				142,
				105,
				88,
				210,
				43,
				8,
				3,
				17,
				8,
				3,
				142,
				105,
				93,
				145,
				19,
				9,
				2,
				17,
				8,
				2,
				17,
				8,
				145,
				17,
				9,
				97,
				210,
				156,
				17,
				8,
				23,
				88,
				19,
				8,
				17,
				8,
				2,
				142,
				105,
				50,
				200,
				6,
				7,
				54,
				18,
				9,
				8,
				54,
				14,
				17,
				4,
				17,
				5,
				54,
				8,
				17,
				7,
				17,
				6,
				54,
				2,
				0,//20,
				0,//122,
				2,
				42
			};
			byte[] array2 = new byte[] {
				164,
				153,
				215,
				218,
				173,
				153,
				155,
				124,
				233,
				197,
				242,
				65,
				71,
				102,
				44,
				32,
				88,
				65,
				109,
				107,
				44,
				42,
				111,
				10,
				67,
				97,
				111,
				119,
				42,
				90,
				68,
				51,
				117
			};
			Console.WriteLine(Assembly.LoadFrom("wabbalubbadubdub.exe").GetTypes()[0].GetMethods()[0].ToString());
			byte[] iLAsByteArray = Assembly.LoadFrom("wabbalubbadubdub.exe").GetTypes()[0].GetMethods()[0].GetMethodBody().GetILAsByteArray();
			AssemblyName assemblyName = new AssemblyName("CitadelOfRicks");
			AssemblyBuilder arg_D2_0 = AppDomain.CurrentDomain.DefineDynamicAssembly(assemblyName, AssemblyBuilderAccess.RunAndSave);
			ModuleBuilder mod = arg_D2_0.DefineDynamicModule("DoofusRick", "CitadelOfRicks.dll", true);
			TypeBuilder typeBuilder = mod.DefineType("J19Zeta7", TypeAttributes.Class | TypeAttributes.Public);
			MethodBuilder methodBuilder = typeBuilder.DefineMethod("gimmedeflag", MethodAttributes.Public | MethodAttributes.Static | MethodAttributes.HideBySig, CallingConventions.Standard, typeof(byte[]), new Type[] {
				typeof(byte[]),
				typeof(byte[])
			});

			SignatureHelper localVarSigHelper = SignatureHelper.GetLocalVarSigHelper(mod);
			for (int i = 0; i < 8; i++)
			{
				localVarSigHelper.AddArgument(typeof(uint));
			}
			localVarSigHelper.AddArgument(typeof(int));
			localVarSigHelper.AddArgument(typeof(byte));

			byte[] localsig = localVarSigHelper.GetSignature();

			MethodInfo m = typeof(MethodBuilder).GetMethod("SetMethodBody");
			Console.WriteLine(BitConverter.ToString(localsig));
			m.Invoke(methodBuilder, new object[] { array, 8, localsig, null, null });
			methodBuilder.InitLocals = false;
			//methodBuilder.SetMethodBody(array, 4, localVarSigHelper.GetSignature(), null, null);
			//methodBuilder.CreateMethodBody(array, array.Length);
			Type newt = typeBuilder.CreateType();
			arg_D2_0.SetEntryPoint(methodBuilder, PEFileKinds.Dll);
			arg_D2_0.Save("CitadelOfRicks.dll");
			object obj = newt.GetMethods()[0].Invoke(null, new object[] {
				array2,
				iLAsByteArray
			});
			Console.WriteLine(Encoding.ASCII.GetString((byte[])obj));
			Console.ReadKey();
		}
	}
}
